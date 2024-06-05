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
     * 6. 게시글 파일 목록 불러오기 (GET)
     * 7. 글 수정 시 파일 업로드 (POST)
     * 8. 글 수정 시 파일 삭제 (DELETE)
     */

    // 1
    @GetMapping("/{bbs_path}")
    fun list(
        @AuthenticationPrincipal rcUser: RcUserJwtClaims,
        @PathVariable("bbs_path") bbsPath: String,
    ): Flux<RestResponseVO<RepBoardListVO>> {
        return boardService.list(rcUser, bbsPath)
    }

    // 2
    @GetMapping("/{bbs_path}/{board_uid}")
    fun detail(
        @AuthenticationPrincipal rcUser: RcUserJwtClaims,
        @PathVariable("bbs_path") bbsPath: String,
        @PathVariable("board_uid") uid: Long,
    ): Mono<RestResponseVO<RepBoardDetailVO>> {
        return boardService.detail(rcUser, bbsPath, uid)
    }

    // 3
    @PostMapping
    fun insert(
        @AuthenticationPrincipal rcUser: RcUserJwtClaims,
        @RequestBody reqBoardInsertVO: ReqBoardInsertVO
    ): Mono<RestResponseVO<Void>> {
        return boardService.insert(rcUser, reqBoardInsertVO)
    }

    // 4
    @PatchMapping
    fun update(
        @AuthenticationPrincipal rcUser: RcUserJwtClaims,
        @RequestBody reqBoardUpdateDTO: ReqBoardUpdateDTO,
    ): Mono<RestResponseVO<Void>> {
        return boardService.update(rcUser, reqBoardUpdateDTO)
    }

    // 5
    @DeleteMapping("/{bbs_path}/{board_uid}")
    fun delete(
        @AuthenticationPrincipal rcUser: RcUserJwtClaims,
        @PathVariable("bbs_path") bbsPath: String,
        @PathVariable("board_uid") boardUid: Long,
    ): Mono<RestResponseVO<Void>> {
        return boardService.delete(rcUser, ReqBoardDeleteVO(bbsPath, boardUid))
    }

    // 6
    @GetMapping("/{bbs_path}/{board_uid}/file")
    fun getBoardFileList(
        @AuthenticationPrincipal rcUser: RcUserJwtClaims,
        @PathVariable("bbs_path") bbsPath: String,
        @PathVariable("board_uid") boardUid: Long,
    ): Flux<RestResponseVO<RepBoardFileListVO>> {
        return boardService.getBoardFileList(rcUser, bbsPath, boardUid)
    }

    // 7
    @PostMapping("/{bbs_path}/{board_uid}/file")
    fun insertBoardFile(
        @AuthenticationPrincipal rcUser: RcUserJwtClaims,
        @PathVariable("bbs_path") bbsPath: String,
        @PathVariable("board_uid") boardUid: Long,
        @RequestBody reqBoardInsertFileVO: List<ReqBoardFileInsertVO>,
    ): Flux<RestResponseVO<RepBoardFileInsertVO>> {
        return boardService.insertBoardFile(rcUser, bbsPath, boardUid, reqBoardInsertFileVO)
    }

    // 8
    @DeleteMapping("/{bbs_path}/{board_uid}/file/{board_file_uid}")
    fun deleteBoardFile(
        @AuthenticationPrincipal rcUser: RcUserJwtClaims,
        @PathVariable("bbs_path") bbsPath: String,
        @PathVariable("board_uid") boardUid: Long,
        @PathVariable("board_file_uid") boardFileUid: List<ReqBoardFileDeleteVO>,
    ): Flux<RestResponseVO<RepBoardFileDeleteVO>> {
        return boardService.deleteBoardFile(rcUser, bbsPath, boardUid, boardFileUid)
    }


}