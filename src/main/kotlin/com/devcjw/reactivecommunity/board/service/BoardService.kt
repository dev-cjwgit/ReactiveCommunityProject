package com.devcjw.reactivecommunity.board.service

import com.devcjw.reactivecommunity.auth.model.RcUser
import com.devcjw.reactivecommunity.auth.model.RcUserJwtClaims
import com.devcjw.reactivecommunity.board.model.domain.BoardRepDetailVO
import com.devcjw.reactivecommunity.board.model.domain.BoardRepListVO
import com.devcjw.reactivecommunity.board.model.domain.BoardReqInsertDTO
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono


interface BoardService {
    fun list(rcUser: RcUser, bbs: String): Flux<BoardRepListVO>
    fun detail(rcUser: RcUser, postUid: Long): Mono<BoardRepDetailVO>
    fun insertPost(rcUser: RcUserJwtClaims, boardReqInsertDTO: BoardReqInsertDTO): Mono<Boolean>
    fun updatePost(rcUser: RcUser, postUid: Long): Mono<Boolean>
    fun deletePost(rcUser: RcUser, postUid: Long): Mono<Boolean>
}