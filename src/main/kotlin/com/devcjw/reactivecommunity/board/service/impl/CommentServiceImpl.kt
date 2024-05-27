package com.devcjw.reactivecommunity.board.service.impl

import com.devcjw.reactivecommunity.auth.model.entity.RcUser
import com.devcjw.reactivecommunity.board.dao.CommentDAO
import com.devcjw.reactivecommunity.board.model.domain.CommentRepListVO
import com.devcjw.reactivecommunity.board.model.domain.CommentReqInsertDTO
import com.devcjw.reactivecommunity.board.model.domain.CommentReqUpdateDTO
import com.devcjw.reactivecommunity.board.service.CommentService
import com.devcjw.reactivecommunity.common.model.RestResponseVO
import lombok.RequiredArgsConstructor
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Service
@RequiredArgsConstructor
class CommentServiceImpl(
    private val commentDAO: CommentDAO
): CommentService {
    override fun list(rcUser: RcUser, boardUid: String): Flux<RestResponseVO<CommentRepListVO>> {
        TODO("Not yet implemented")
    }

    override fun insert(rcUser: RcUser, commentReqInsertDTO: CommentReqInsertDTO): Mono<RestResponseVO<Void>> {
        TODO("Not yet implemented")
    }

    override fun update(rcUser: RcUser, commentReqUpdateDTO: CommentReqUpdateDTO): Mono<RestResponseVO<Void>> {
        TODO("Not yet implemented")
    }

    override fun delete(rcUser: RcUser, commentUid: Long): Mono<RestResponseVO<Void>> {
        TODO("Not yet implemented")
    }
}