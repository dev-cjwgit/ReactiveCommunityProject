package com.devcjw.reactivecommunity.board.controller

import com.devcjw.reactivecommunity.auth.model.domain.RcUserJwtClaims
import com.devcjw.reactivecommunity.board.model.domain.*
import com.devcjw.reactivecommunity.board.service.BoardService
import com.devcjw.reactivecommunity.common.model.RestResponseVO
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
    @GetMapping("/{bbs_path}")
    fun list(
            @AuthenticationPrincipal rcUser: RcUserJwtClaims,
            @PathVariable("bbs_path") bbsPath: String,
    ): Flux<RestResponseVO<BoardRepListVO>> {
        return boardService.list(rcUser, bbsPath)
    }

    // 2
    @GetMapping("/{bbs_path}/{board_uid}")
    fun detail(
        @AuthenticationPrincipal rcUser: RcUserJwtClaims,
        @PathVariable("bbs_path") bbsPath: String,
        @PathVariable("board_uid") uid: Long,
    ): Mono<RestResponseVO<BoardRepDetailVO>> {
        return boardService.detail(rcUser, bbsPath, uid)
    }

    // 3
    @PostMapping
    fun insert(
            @AuthenticationPrincipal rcUser: RcUserJwtClaims,
            @RequestBody boardReqInsertDTO: BoardReqInsertDTO
    ): Mono<RestResponseVO<Void>> {
        return boardService.insert(rcUser, boardReqInsertDTO)
    }

    // 4
    @PatchMapping
    fun update(
            @AuthenticationPrincipal rcUser: RcUserJwtClaims,
            @RequestBody boardReqUpdateDTO: BoardReqUpdateDTO,
    ): Mono<RestResponseVO<Void>> {
        return boardService.update(rcUser, boardReqUpdateDTO)
    }

    // 5
    @DeleteMapping("/{bbs_path}/{board_uid}")
    fun delete(
            @AuthenticationPrincipal rcUser: RcUserJwtClaims,
            @PathVariable("bbs_path") bbsPath: String,
            @PathVariable("board_uid") boardUid: Long,
    ): Mono<RestResponseVO<Void>> {
        return boardService.delete(rcUser, BoardReqDeleteDTO(bbsPath, boardUid))
    }

}