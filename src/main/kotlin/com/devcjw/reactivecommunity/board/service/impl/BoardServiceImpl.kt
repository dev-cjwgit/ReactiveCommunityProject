package com.devcjw.reactivecommunity.board.service.impl

import com.devcjw.reactivecommunity.auth.model.RcUser
import com.devcjw.reactivecommunity.auth.model.RcUserJwtClaims
import com.devcjw.reactivecommunity.board.dao.BoardDAO
import com.devcjw.reactivecommunity.board.model.domain.BoardRepDetailVO
import com.devcjw.reactivecommunity.board.model.domain.BoardRepListVO
import com.devcjw.reactivecommunity.board.model.domain.BoardReqInsertDTO
import com.devcjw.reactivecommunity.board.model.entity.BoardInsertDTO
import com.devcjw.reactivecommunity.board.service.BoardService
import lombok.RequiredArgsConstructor
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Service
@RequiredArgsConstructor
class BoardServiceImpl(
    private val boardDAO: BoardDAO,
) : BoardService {
    override fun list(rcUser: RcUser, bbs: String): Flux<BoardRepListVO> {
        TODO("Not yet implemented")
    }

    override fun detail(rcUser: RcUser, postUid: Long): Mono<BoardRepDetailVO> {
        TODO("Not yet implemented")
    }

    override fun insertPost(rcUser: RcUserJwtClaims, boardReqInsertDTO: BoardReqInsertDTO): Mono<Boolean> {
        /**
         * 1. Entity 생성
         * 2. 데이터 삽입
         */
        return Mono.just(rcUser)
            .flatMap {
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
                boardDAO.insertPost(it).thenReturn(it)
            }
            .then(Mono.defer { Mono.just(true) })
    }

    override fun updatePost(rcUser: RcUser, postUid: Long): Mono<Boolean> {
        TODO("Not yet implemented")
    }

    override fun deletePost(rcUser: RcUser, postUid: Long): Mono<Boolean> {
        TODO("Not yet implemented")
    }

}