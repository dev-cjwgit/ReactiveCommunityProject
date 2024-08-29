package com.devcjw.reactivecommunity.board.controller

import com.devcjw.reactivecommunity.auth.model.RcUserJwtClaims
import com.devcjw.reactivecommunity.board.model.domain.RepCommentListVO
import com.devcjw.reactivecommunity.board.model.domain.ReqCommentInsertDTO
import com.devcjw.reactivecommunity.board.model.domain.ReqCommentUpdateVO
import com.devcjw.reactivecommunity.board.service.CommentRestService
import com.devcjw.reactivecommunity.common.model.RestResponseVO
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@RestController
@RequestMapping("/comment")
@Tag(name = "댓글 컨트롤러", description = "댓글의 CRUD 등을 담당하는 컨트롤러")
class CommentRestController(
    private val commentRestService: CommentRestService
) {
    /**
     * 1. 댓글 리스트 조회 (GET) boardUid
     * 2. 댓글 추가 (POST) boardUid
     * 3. 댓글 수정 (PATCH) commentUid
     * 4. 댓글 삭제 (DELETE) commentUid
     */

    // 1
    @GetMapping("/{board_uid}")
    @Operation(summary = "댓글 목록", description = "특정 게시글의 댓글 목록을 불러오는 API")
    fun list(
        @AuthenticationPrincipal rcUser: RcUserJwtClaims,
        @PathVariable("board_uid") boardUid: Long,
    ): Flux<RestResponseVO<RepCommentListVO>> {
        return commentRestService.list(rcUser, boardUid)
    }

    // 2
    @PostMapping
    @Operation(summary = "댓글 쓰기", description = "특정 게시글에 댓글을 쓰는 API")
    fun insert(
        @AuthenticationPrincipal rcUser: RcUserJwtClaims,
        @RequestBody reqCommentInsertDTO: ReqCommentInsertDTO,
    ): Mono<RestResponseVO<Void>> {
        return commentRestService.insert(rcUser, reqCommentInsertDTO)
    }

    // 3
    @PatchMapping
    @Operation(summary = "댓글 수정", description = "특정 게시글에 댓글을 수정하는 API")
    fun update(
        @AuthenticationPrincipal rcUser: RcUserJwtClaims,
        @RequestBody reqCommentUpdateVO: ReqCommentUpdateVO,
    ): Mono<RestResponseVO<Void>> {
        return commentRestService.update(rcUser, reqCommentUpdateVO)
    }

    @DeleteMapping("/{uid}")
    @Operation(summary = "댓글 삭제", description = "특정 게시글에 댓글을 삭제하는 API")
    fun delete(
        @AuthenticationPrincipal rcUser: RcUserJwtClaims,
        @PathVariable("uid") uid: Long,
    ): Mono<RestResponseVO<Void>> {
        return commentRestService.delete(rcUser, uid)
    }
}