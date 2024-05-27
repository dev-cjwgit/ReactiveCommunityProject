package com.devcjw.reactivecommunity.board.service.impl

import com.devcjw.reactivecommunity.auth.model.domain.RcUserJwtClaims
import com.devcjw.reactivecommunity.auth.model.entity.RcUser
import com.devcjw.reactivecommunity.board.dao.BoardDAO
import com.devcjw.reactivecommunity.board.model.domain.BoardRepDetailVO
import com.devcjw.reactivecommunity.board.model.domain.BoardRepListVO
import com.devcjw.reactivecommunity.board.model.domain.BoardReqInsertDTO
import com.devcjw.reactivecommunity.board.model.domain.BoardReqUpdateDTO
import com.devcjw.reactivecommunity.board.model.entity.BoardInsertDTO
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
    override fun list(rcUser: RcUser, bbs: String): Flux<RestResponseVO<BoardRepListVO>> {
        TODO("Not yet implemented")
    }

    override fun detail(rcUser: RcUser, postUid: Long): Mono<RestResponseVO<BoardRepDetailVO>> {
        TODO("Not yet implemented")
    }

    override fun insertPost(rcUser: RcUserJwtClaims, boardReqInsertDTO: BoardReqInsertDTO): Mono<RestResponseVO<Void>> {
        /**
         * 1. 게시판 존재 확인
         * 2. Entity 생성
         * 3. 데이터 삽입
         */
        return Mono.just(rcUser)
            .flatMap {
                boardDAO.isBbsBoard(boardReqInsertDTO.bbsUid)
            }
            .filter { exists -> exists }
            .switchIfEmpty(Mono.error(RcException(RcErrorMessage.NOT_FOUND_BBS_BOARD_EXCEPTION)))
            .flatMap {
                // 2
                Mono.just(
                    BoardInsertDTO(
                        boardReqInsertDTO.bbsUid,
                        boardReqInsertDTO.title,
                        boardReqInsertDTO.contents,
                        rcUser.uid
                    )
                )
            }
            .flatMap {
                // 3
                boardDAO.insertPost(it).thenReturn(it)
            }
            .then(Mono.defer { Mono.just(RestResponseVO(true)) })
    }

    override fun updatePost(rcUser: RcUser, boardReqUpdateDTO: BoardReqUpdateDTO): Mono<RestResponseVO<Void>> {
        TODO("Not yet implemented")
    }

    override fun deletePost(rcUser: RcUser, postUid: Long): Mono<RestResponseVO<Void>> {
        TODO("Not yet implemented")
    }

}