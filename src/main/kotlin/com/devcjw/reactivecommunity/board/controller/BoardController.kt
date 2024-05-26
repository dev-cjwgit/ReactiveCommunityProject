package com.devcjw.reactivecommunity.board.controller

import com.devcjw.reactivecommunity.auth.model.RcUser
import com.devcjw.reactivecommunity.auth.model.RcUserJwtClaims
import com.devcjw.reactivecommunity.board.model.domain.BoardRepDetailVO
import com.devcjw.reactivecommunity.board.model.domain.BoardRepListVO
import com.devcjw.reactivecommunity.board.model.domain.BoardReqInsertDTO
import com.devcjw.reactivecommunity.board.service.BoardService
import lombok.RequiredArgsConstructor
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@RestController
@RequestMapping("/board")
@RequiredArgsConstructor
class BoardController(
    private val boardService: BoardService
) {
    /**
     * 1. 글 리스트 조회 (GET)
     * 2. 글 상세 조회 (GET)
     * 3. 글 쓰기 (POST)
     * 4. 글 수정 (PATCH)
     * 5. 글 삭제 (DELETE)
     */

    // 1
    @GetMapping("/{bbs}")
    fun list(
        @AuthenticationPrincipal rcUser: RcUser,
        @PathVariable("bbs") bbs: String,
    ): Flux<BoardRepListVO> {
        return boardService.list(rcUser, bbs)
    }

    // 2
    @GetMapping("/{post_uid}")
    fun detail(
        @AuthenticationPrincipal rcUser: RcUser,
        @PathVariable("post_uid") postUid: Long,
    ): Mono<BoardRepDetailVO> {
        return boardService.detail(rcUser, postUid)
    }

    // 3
    @PostMapping
    fun insertPost(
        @AuthenticationPrincipal rcUser: RcUserJwtClaims,
        @RequestBody boardReqInsertDTO: BoardReqInsertDTO
    ): Mono<Boolean> {
        return boardService.insertPost(rcUser, boardReqInsertDTO)
    }

    @PatchMapping("/{post_uid}")
    fun updatePost(
        @AuthenticationPrincipal rcUser: RcUser,
        @PathVariable("post_uid") postUid: Long,
    ): Mono<Boolean> {
        return boardService.updatePost(rcUser, postUid)
    }

    @DeleteMapping("/{post_uid}")
    fun deletePost(
        @AuthenticationPrincipal rcUser: RcUser,
        @PathVariable("post_uid") postUid: Long,
    ): Mono<Boolean> {
        return boardService.deletePost(rcUser, postUid)
    }

}