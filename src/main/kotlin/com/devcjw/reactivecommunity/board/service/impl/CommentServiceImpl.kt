package com.devcjw.reactivecommunity.board.service.impl

import com.devcjw.reactivecommunity.auth.model.domain.RcUserJwtClaims
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
) : CommentService {
    override fun list(rcUser: RcUserJwtClaims, boardUid: Long): Flux<RestResponseVO<CommentRepListVO>> {
        return commentDAO.selectList(boardUid)
            .flatMap {
                Mono.just(
                    RestResponseVO(
                        result = true,
                        data = CommentRepListVO(it.uid, it.contents, it.createdAt, it.updatedAt)
                    )
                )
            }
    }

    override fun insert(rcUser: RcUserJwtClaims, commentReqInsertDTO: CommentReqInsertDTO): Mono<RestResponseVO<Void>> {
        TODO("Not yet implemented")
    }

    override fun update(rcUser: RcUserJwtClaims, commentReqUpdateDTO: CommentReqUpdateDTO): Mono<RestResponseVO<Void>> {
        TODO("Not yet implemented")
    }

    override fun delete(rcUser: RcUserJwtClaims, commentUid: Long): Mono<RestResponseVO<Void>> {
        TODO("Not yet implemented")
    }
}