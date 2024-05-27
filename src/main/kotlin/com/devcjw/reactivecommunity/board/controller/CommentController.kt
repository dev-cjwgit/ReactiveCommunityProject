package com.devcjw.reactivecommunity.board.controller

import com.devcjw.reactivecommunity.auth.model.entity.RcUser
import com.devcjw.reactivecommunity.board.model.domain.CommentRepListVO
import com.devcjw.reactivecommunity.board.model.domain.CommentReqInsertDTO
import com.devcjw.reactivecommunity.board.model.domain.CommentReqUpdateDTO
import com.devcjw.reactivecommunity.board.service.CommentService
import com.devcjw.reactivecommunity.common.model.RestResponseVO
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@RestController
@RequestMapping("/comment")
class CommentController(
    private val commentService: CommentService
) {
    /**
     * 1. 댓글 리스트 조회 (GET) boardUid
     * 2. 댓글 추가 (POST) boardUid
     * 3. 댓글 수정 (PATCH) commentUid
     * 4. 댓글 삭제 (DELETE) commentUid
     */

    // 1
    @GetMapping("/{board_uid}")
    fun list(
        @AuthenticationPrincipal rcUser: RcUser,
        @PathVariable("board_uid") boardUid: String,
    ) : Flux<RestResponseVO<CommentRepListVO>> {
        TODO("Not yet implemented")
    }

    // 2
    @PostMapping
    fun insert(
        @AuthenticationPrincipal rcUser: RcUser,
        @RequestBody commentReqInsertDTO: CommentReqInsertDTO,
    ): Mono<RestResponseVO<Void>> {
        TODO("Not yet implemented")
    }

    // 3
    @PatchMapping
    fun update(
        @AuthenticationPrincipal rcUser: RcUser,
        @RequestBody commentReqUpdateDTO: CommentReqUpdateDTO,
    ): Mono<RestResponseVO<Void>> {
        TODO("Not yet implemented")
    }

    @DeleteMapping("/{comment_uid}")
    fun delete(
        @AuthenticationPrincipal rcUser: RcUser,
        @PathVariable("comment_uid") commentUid: Long,
    ): Mono<RestResponseVO<Void>> {
        TODO("Not yet implemented")
    }

}