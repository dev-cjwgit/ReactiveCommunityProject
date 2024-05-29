package com.devcjw.reactivecommunity.board.service.impl

import com.devcjw.reactivecommunity.auth.model.domain.RcUserJwtClaims
import com.devcjw.reactivecommunity.board.dao.BoardDAO
import com.devcjw.reactivecommunity.board.model.domain.*
import com.devcjw.reactivecommunity.board.model.entity.BoardInsertDTO
import com.devcjw.reactivecommunity.board.model.entity.BoardUpdateDTO
import com.devcjw.reactivecommunity.board.service.BoardService
import com.devcjw.reactivecommunity.common.exception.config.RcException
import com.devcjw.reactivecommunity.common.exception.model.RcErrorMessage
import com.devcjw.reactivecommunity.common.model.RestResponseVO
import lombok.RequiredArgsConstructor
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Service
@RequiredArgsConstructor
class BoardServiceImpl(
        private val boardDAO: BoardDAO,
) : BoardService {
    override fun list(rcUser: RcUserJwtClaims, bbsPath: String): Flux<RestResponseVO<BoardRepListVO>> {
        return boardDAO.isBbsPath(bbsPath)
                .filter { exists -> exists }
                .switchIfEmpty(Mono.error(RcException(RcErrorMessage.NOT_FOUND_BBS_BOARD_EXCEPTION)))
                .flatMapMany {
                    boardDAO.selectList(bbsPath)
                }
                .map {
                    val boardRepListVO = BoardRepListVO(it.uid, it.title, it.writerNickname, it.hit, it.createdAt, it.updatedAt)
                    RestResponseVO(
                            result = true,
                            data = boardRepListVO
                    )
                }
    }

    override fun detail(
            rcUser: RcUserJwtClaims,
            bbsPath: String,
            boardUid: Long
    ): Mono<RestResponseVO<BoardRepDetailVO>> {
        return boardDAO.isBbsPath(bbsPath)
                .filter { exists -> exists }
                .switchIfEmpty(Mono.error(RcException(RcErrorMessage.NOT_FOUND_BBS_BOARD_EXCEPTION)))
                .flatMap {
                    boardDAO.selectDetail(boardUid)
                }
                .map {
                    BoardRepDetailVO(
                            uid = it.uid,
                            title = it.title,
                            contents = it.contents,
                            writerNickname = it.writerNickname,
                            hit = it.hit,
                            createdAt = it.createdAt,
                            updatedAt = it.updatedAt
                    )
                }
                .map {
                    RestResponseVO(
                            result = true,
                            data = it
                    )
                }
    }

    override fun insert(rcUser: RcUserJwtClaims, boardReqInsertDTO: BoardReqInsertDTO): Mono<RestResponseVO<Void>> {
        /**
         * 1. 게시판 존재 확인
         * 2. Entity 생성
         * 3. 데이터 삽입
         */
        return Mono.just(rcUser)
                .flatMap {
                    boardDAO.isBbsUid(boardReqInsertDTO.bbsUid)
                }
                .filter { exists -> exists }
                .switchIfEmpty(Mono.error(RcException(RcErrorMessage.NOT_FOUND_BBS_BOARD_EXCEPTION)))
                .map {
                    // 2
                    BoardInsertDTO(
                            boardReqInsertDTO.bbsUid,
                            boardReqInsertDTO.title,
                            boardReqInsertDTO.contents,
                            rcUser.uid
                    )
                }
                .flatMap {
                    // 3
                    boardDAO.insert(it)
                }
                .then(Mono.defer { Mono.just(RestResponseVO(true)) })
    }

    override fun update(rcUser: RcUserJwtClaims, boardReqUpdateDTO: BoardReqUpdateDTO): Mono<RestResponseVO<Void>> {
        /**
         * 1. BBS Path 체크
         * 2. 게시글 확인
         * 3. 작성자가 맞는지 확인
         * 4. 수정 DB
         */
        return boardDAO.isBbsPath(boardReqUpdateDTO.bbsPath)
                // 1
                .filter { exists -> exists }
                .switchIfEmpty(Mono.error(RcException(RcErrorMessage.NOT_FOUND_BBS_BOARD_EXCEPTION)))
                // 2
                .filterWhen {
                    boardDAO.isBoardUid(boardReqUpdateDTO.uid)
                }
                .switchIfEmpty(Mono.error(RcException(RcErrorMessage.NOT_FOUND_BOARD_EXCEPTION)))
                // 3
                .filterWhen {
                    boardDAO.isWriterBoard(boardReqUpdateDTO.uid, rcUser.uid)
                }
                .switchIfEmpty(Mono.error(RcException(RcErrorMessage.NOT_MATCH_WRITER_UID_EXCEPTION)))
                .map {
                    BoardUpdateDTO(
                            boardReqUpdateDTO.uid,
                            boardReqUpdateDTO.title,
                            boardReqUpdateDTO.contents
                    )
                }
                // 4
                .flatMap {
                    boardDAO.update(it)
                }
                .then(Mono.defer { Mono.just(RestResponseVO(true)) })
    }

    override fun delete(rcUser: RcUserJwtClaims, boardReqDeleteDTO: BoardReqDeleteDTO): Mono<RestResponseVO<Void>> {
        /**
         * 1. BBS Path 체크
         * 2. 게시글 확인
         * 3. 작성자가 맞는지 확인
         * 4. 삭제 DB
         */
        return boardDAO.isBbsPath(boardReqDeleteDTO.bbsPath)
                // 1
                .filter { exists -> exists }
                .switchIfEmpty(Mono.error(RcException(RcErrorMessage.NOT_FOUND_BBS_BOARD_EXCEPTION)))
                // 2
                .filterWhen {
                    boardDAO.isBoardUid(boardReqDeleteDTO.uid)
                }
                .switchIfEmpty(Mono.error(RcException(RcErrorMessage.NOT_FOUND_BOARD_EXCEPTION)))
                // 3
                .filterWhen {
                    boardDAO.isWriterBoard(boardReqDeleteDTO.uid, rcUser.uid)
                }
                .switchIfEmpty(Mono.error(RcException(RcErrorMessage.NOT_MATCH_WRITER_UID_EXCEPTION)))
                // 4
                .flatMap {
                    boardDAO.delete(boardReqDeleteDTO.uid)
                }
                .then(Mono.defer { Mono.just(RestResponseVO(true)) })
    }

}