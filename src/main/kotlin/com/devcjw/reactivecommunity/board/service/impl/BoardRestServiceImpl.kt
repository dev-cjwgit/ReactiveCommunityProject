package com.devcjw.reactivecommunity.board.service.impl

import com.devcjw.reactivecommunity.auth.model.RcUserJwtClaims
import com.devcjw.reactivecommunity.board.dao.BoardDAO
import com.devcjw.reactivecommunity.board.model.domain.*
import com.devcjw.reactivecommunity.board.model.entity.InBoardInsertFileVO
import com.devcjw.reactivecommunity.board.model.entity.InBoardInsertVO
import com.devcjw.reactivecommunity.board.model.entity.InBoardUpdateVO
import com.devcjw.reactivecommunity.board.service.BoardRestService
import com.devcjw.reactivecommunity.common.exception.config.RcException
import com.devcjw.reactivecommunity.common.exception.model.RcErrorMessage
import com.devcjw.reactivecommunity.common.model.RestResponseVO
import lombok.RequiredArgsConstructor
import org.springframework.dao.DataIntegrityViolationException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Service
@RequiredArgsConstructor
class BoardRestServiceImpl(
        private val boardDAO: BoardDAO,
) : BoardRestService {
    override fun list(rcUser: RcUserJwtClaims, bbsPath: String): Flux<RestResponseVO<RepBoardListVO>> {
        /**
         * 1. 게시판 존재 확인
         * 2. 게시글 목록 가져오기
         * 3. 결과 맵핑 및 반환
         */
        return boardDAO.isBbsPath(bbsPath)
                // 1
                .filter { exists -> exists }
                .switchIfEmpty(Mono.error(RcException(RcErrorMessage.NOT_FOUND_BBS_BOARD_EXCEPTION)))
                // 2
                .flatMapMany { boardDAO.selectList(bbsPath) }
                .map {
                    // 3
                    val repBoardListVO =
                            RepBoardListVO(it.uid, it.title, it.createdUserNickname, it.hit, it.createdUtcAt, it.updatedUtcAt)
                    RestResponseVO(
                            result = true,
                            data = repBoardListVO
                    )
                }
    }

    override fun detail(
            rcUser: RcUserJwtClaims,
            uid: Long
    ): Mono<RestResponseVO<RepBoardDetailVO>> {
        /**
         * 1. 게시글 존재 확인
         * 2. 데이터 가져오기
         * 3. 결과 맵핑
         * 4. 결과 반환
         */
        // 1
        return boardDAO.isBoardUid(uid)
                .switchIfEmpty(Mono.error(RcException(RcErrorMessage.NOT_FOUND_BOARD_EXCEPTION)))
                // 2
                .flatMap { boardDAO.selectDetail(uid) }
                // 3
                .map {
                    RepBoardDetailVO(
                            uid = it.uid,
                            title = it.title,
                            contents = it.contents,
                            writerNickname = it.writerNickname,
                            hit = it.hit,
                            createdUtcAt = it.createdUtcAt,
                            updatedUtcAt = it.updatedUtcAt
                    )
                }
                // 4
                .map {
                    RestResponseVO(
                            result = true,
                            data = it
                    )
                }
    }

    @Transactional
    override fun insert(rcUser: RcUserJwtClaims, reqBoardInsertVO: ReqBoardInsertVO): Mono<RestResponseVO<Void>> {
        /**
         * 1. 게시판 존재 확인
         * 2. Entity 생성
         * 3. 데이터 삽입
         * 4. 첨부파일 연결
         * 5. 성공 반환
         */
        return Mono.just(rcUser)
                // 1
                .flatMap { boardDAO.isBbsUid(reqBoardInsertVO.bbsUid) }
                .filter { exists -> exists }
                .switchIfEmpty(Mono.error(RcException(RcErrorMessage.NOT_FOUND_BBS_BOARD_EXCEPTION)))
                // 2
                .map {
                    // 2
                    InBoardInsertVO(
                            reqBoardInsertVO.bbsUid,
                            reqBoardInsertVO.title,
                            reqBoardInsertVO.contents,
                            rcUser.uid
                    )
                }
                // 3
                .flatMap { boardDAO.insert(it) }
                // 4
                .flatMap { boardUid ->
                    reqBoardInsertVO.files?.let { files ->
                        Flux.fromIterable(files)
                                .flatMap { file ->
                                    boardDAO.insertFile(InBoardInsertFileVO(boardUid, file.fileUid, file.fileName))
                                }
                                .then(Mono.just(boardUid))
                    } ?: Mono.just(boardUid)
                }
                // 5
                .then<RestResponseVO<Void>?>(Mono.defer { Mono.just(RestResponseVO(true)) })
                .onErrorResume {
                    if (it is DataIntegrityViolationException) {
                        Mono.error(RcException(RcErrorMessage.INVALID_FILE_UID_EXCEPTION))
                    } else {
                        Mono.error(it)
                    }
                }
    }

    override fun update(rcUser: RcUserJwtClaims, reqBoardUpdateDTO: ReqBoardUpdateDTO): Mono<RestResponseVO<Void>> {
        /**
         * 1. BBS Path 체크
         * 2. 게시글 확인
         * 3. 작성자가 맞는지 확인
         * 4. 수정 DB
         */
        return boardDAO.isBbsPath(reqBoardUpdateDTO.bbsPath)
                // 1
                .filter { exists -> exists }
                .switchIfEmpty(Mono.error(RcException(RcErrorMessage.NOT_FOUND_BBS_BOARD_EXCEPTION)))
                // 2
                .filterWhen {
                    boardDAO.isBoardUid(reqBoardUpdateDTO.uid)
                }
                .switchIfEmpty(Mono.error(RcException(RcErrorMessage.NOT_FOUND_BOARD_EXCEPTION)))
                // 3
                .filterWhen {
                    boardDAO.isWriterBoard(reqBoardUpdateDTO.uid, rcUser.uid)
                }
                .switchIfEmpty(Mono.error(RcException(RcErrorMessage.NOT_MATCH_WRITER_UID_EXCEPTION)))
                .map {
                    InBoardUpdateVO(
                            reqBoardUpdateDTO.uid,
                            reqBoardUpdateDTO.title,
                            reqBoardUpdateDTO.contents
                    )
                }
                // 4
                .flatMap {
                    boardDAO.update(it)
                }
                .then(Mono.defer { Mono.just(RestResponseVO(true)) })
    }

    override fun delete(rcUser: RcUserJwtClaims, boardUid: Long): Mono<RestResponseVO<Void>> {
        /**
         * 1. BBS Path 체크
         * 2. 게시글 확인
         * 3. 작성자가 맞는지 확인
         * 4. 삭제 DB
         */
        return boardDAO.isBoardUid(boardUid)
                .switchIfEmpty(Mono.error(RcException(RcErrorMessage.NOT_FOUND_BOARD_EXCEPTION)))
                // 3
                .filterWhen {
                    boardDAO.isWriterBoard(boardUid, rcUser.uid)
                }
                .switchIfEmpty(Mono.error(RcException(RcErrorMessage.NOT_MATCH_WRITER_UID_EXCEPTION)))
                // 4
                .flatMap {
                    boardDAO.delete(boardUid)
                }
                .then(Mono.defer { Mono.just(RestResponseVO(true)) })
    }

    override fun getBoardFileList(
            rcUser: RcUserJwtClaims,
            boardUid: Long
    ): Flux<RestResponseVO<RepBoardFileListVO>> {
        /**
         * 1. 게시글 확인
         * 2. DB 조회 및 반환
         */
        return boardDAO.isBoardUid(boardUid)
                .switchIfEmpty(Mono.error(RcException(RcErrorMessage.NOT_FOUND_BOARD_EXCEPTION)))
                .flatMapMany {
                    boardDAO.selectFileList(boardUid)
                            .map { RepBoardFileListVO(it.uid, it.fileUid, it.fileName, it.fileSize) }
                            .map { RestResponseVO(result = true, data = it) }
                }
    }

    override fun insertBoardFile(
            rcUser: RcUserJwtClaims,
            boardUid: Long,
            reqBoardInsertFileVO: List<ReqBoardFileInsertVO>
    ): Flux<RestResponseVO<RepBoardFileInsertVO>> {
        /**
         * 1. 게시글 확인
         * 2. 작성자가 맞는지 확인
         * 3. 첨부파일 연결
         */
        return boardDAO.isBoardUid(boardUid)
                .switchIfEmpty(Mono.error(RcException(RcErrorMessage.NOT_FOUND_BOARD_EXCEPTION)))
                // 3
                .filterWhen {
                    boardDAO.isWriterBoard(boardUid, rcUser.uid)
                }
                .switchIfEmpty(Mono.error(RcException(RcErrorMessage.NOT_MATCH_WRITER_UID_EXCEPTION)))
                // 4
                .flatMapMany {
                    Flux.fromIterable(reqBoardInsertFileVO)
                            .flatMap { file ->
                                boardDAO.insertFile(InBoardInsertFileVO(boardUid, file.fileUid, file.fileName))
                                        .then(
                                                Mono.just(
                                                        RestResponseVO(
                                                                result = true,
                                                                data = RepBoardFileInsertVO(file.order)
                                                        )
                                                )
                                        )
                                        .onErrorResume {
                                            if (it is DataIntegrityViolationException) {
                                                Mono.just(
                                                        RestResponseVO(
                                                                result = false,
                                                                data = RepBoardFileInsertVO(file.order),
                                                                message = RcErrorMessage.INVALID_FILE_UID_EXCEPTION.message
                                                        )
                                                )
                                            } else {
                                                Mono.error(it)
                                            }
                                        }
                            }
                }

    }

    override fun deleteBoardFile(
            rcUser: RcUserJwtClaims,
            boardUid: Long,
            boardFileUid: List<ReqBoardFileDeleteVO>
    ): Flux<RestResponseVO<RepBoardFileDeleteVO>> {
        /**
         * 1. 게시글 확인
         * 2. 작성자가 맞는지 확인
         * 3. 파일이 존재하는지 확인
         * 4. 삭제 DB
         */
        return boardDAO.isBoardUid(boardUid)
                .switchIfEmpty(Mono.error(RcException(RcErrorMessage.NOT_FOUND_BOARD_EXCEPTION)))
                // 3
                .filterWhen {
                    boardDAO.isWriterBoard(boardUid, rcUser.uid)
                }
                .switchIfEmpty(Mono.error(RcException(RcErrorMessage.NOT_MATCH_WRITER_UID_EXCEPTION)))

                .flatMapMany {
                    Flux.fromIterable(boardFileUid)
                            .flatMap { file ->
                                // 4
                                boardDAO.isExistBoardFile(boardUid, file.fileUid)
                                        .flatMap { exists ->
                                            if (exists) {
                                                // 5
                                                boardDAO.deleteFile(boardUid, file.fileUid)
                                                        .then(
                                                                Mono.just(
                                                                        RestResponseVO(
                                                                                result = true,
                                                                                data = RepBoardFileDeleteVO(boardUid, file.fileUid)
                                                                        )
                                                                )
                                                        )
                                            } else {
                                                Mono.just(
                                                        RestResponseVO(
                                                                result = false,
                                                                data = RepBoardFileDeleteVO(boardUid, file.fileUid),
                                                                message = RcErrorMessage.INVALID_FILE_UID_EXCEPTION.message
                                                        )
                                                )
                                            }
                                        }
                                        .onErrorResume {
                                            if (it is DataIntegrityViolationException) {
                                                Mono.just(
                                                        RestResponseVO(
                                                                result = false,
                                                                data = RepBoardFileDeleteVO(boardUid, file.fileUid),
                                                                message = RcErrorMessage.INVALID_FILE_UID_EXCEPTION.message
                                                        )
                                                )
                                            } else {
                                                Mono.error(it)
                                            }
                                        }
                            }
                }
    }
}