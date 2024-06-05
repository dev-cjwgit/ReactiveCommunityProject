package com.devcjw.reactivecommunity.board.service.impl

import com.devcjw.reactivecommunity.auth.model.domain.RcUserJwtClaims
import com.devcjw.reactivecommunity.board.dao.BoardDAO
import com.devcjw.reactivecommunity.board.model.domain.*
import com.devcjw.reactivecommunity.board.model.entity.InBoardInsertFileVO
import com.devcjw.reactivecommunity.board.model.entity.InBoardInsertVO
import com.devcjw.reactivecommunity.board.model.entity.InBoardUpdateVO
import com.devcjw.reactivecommunity.board.service.BoardService
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
class BoardServiceImpl(
    private val boardDAO: BoardDAO,
) : BoardService {
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
                    RepBoardListVO(it.uid, it.title, it.writerNickname, it.hit, it.createdAt, it.updatedAt)
                RestResponseVO(
                    result = true,
                    data = repBoardListVO
                )
            }
    }

    override fun detail(
        rcUser: RcUserJwtClaims,
        bbsPath: String,
        uid: Long
    ): Mono<RestResponseVO<RepBoardDetailVO>> {
        /**
         * 1. 게시판 존재 확인
         * 2. 게시글 존재 확인
         * 3. 데이터 가져오기
         * 4. 결과 맵핑
         * 5. 결과 반환
         */
        return boardDAO.isBbsPath(bbsPath)
            // 1
            .filter { exists -> exists }
            .switchIfEmpty(Mono.error(RcException(RcErrorMessage.NOT_FOUND_BBS_BOARD_EXCEPTION)))
            // 2
            .filterWhen { boardDAO.isBoardUid(uid) }
            .switchIfEmpty(Mono.error(RcException(RcErrorMessage.NOT_FOUND_BOARD_EXCEPTION)))
            // 3
            .flatMap { boardDAO.selectDetail(uid) }
            // 4
            .map {
                RepBoardDetailVO(
                    uid = it.uid,
                    title = it.title,
                    contents = it.contents,
                    writerNickname = it.writerNickname,
                    hit = it.hit,
                    createdAt = it.createdAt,
                    updatedAt = it.updatedAt
                )
            }
            // 5
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
                        .then(Mono.just(boardUid))  // To continue the chain after processing files
                } ?: Mono.just(boardUid)  // If there are no files, continue with the UID
            }
            // 5
            .then<RestResponseVO<Void>?>(Mono.defer { Mono.just(RestResponseVO(true)) })
            .onErrorResume {
                if(it is DataIntegrityViolationException){
                    Mono.error(RcException(RcErrorMessage.INVALID_FILE_UID_EXCEPTION))
                }else{
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

    override fun delete(rcUser: RcUserJwtClaims, reqBoardDeleteVO: ReqBoardDeleteVO): Mono<RestResponseVO<Void>> {
        /**
         * 1. BBS Path 체크
         * 2. 게시글 확인
         * 3. 작성자가 맞는지 확인
         * 4. 삭제 DB
         */
        return boardDAO.isBbsPath(reqBoardDeleteVO.bbsPath)
            // 1
            .filter { exists -> exists }
            .switchIfEmpty(Mono.error(RcException(RcErrorMessage.NOT_FOUND_BBS_BOARD_EXCEPTION)))
            // 2
            .filterWhen {
                boardDAO.isBoardUid(reqBoardDeleteVO.uid)
            }
            .switchIfEmpty(Mono.error(RcException(RcErrorMessage.NOT_FOUND_BOARD_EXCEPTION)))
            // 3
            .filterWhen {
                boardDAO.isWriterBoard(reqBoardDeleteVO.uid, rcUser.uid)
            }
            .switchIfEmpty(Mono.error(RcException(RcErrorMessage.NOT_MATCH_WRITER_UID_EXCEPTION)))
            // 4
            .flatMap {
                boardDAO.delete(reqBoardDeleteVO.uid)
            }
            .then(Mono.defer { Mono.just(RestResponseVO(true)) })
    }

}