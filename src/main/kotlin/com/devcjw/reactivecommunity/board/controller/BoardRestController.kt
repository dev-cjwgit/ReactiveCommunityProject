package com.devcjw.reactivecommunity.board.controller

import com.devcjw.reactivecommunity.auth.model.RcUserJwtClaims
import com.devcjw.reactivecommunity.board.model.domain.*
import com.devcjw.reactivecommunity.board.service.BoardRestService
import com.devcjw.reactivecommunity.common.model.RestResponseVO
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import lombok.RequiredArgsConstructor
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@RestController
@RequestMapping("/board")
@RequiredArgsConstructor
@Tag(name = "게시글 컨트롤러", description = "게시판 CRUD 등을 담당하는 컨트롤러")
class BoardRestController(
    private val boardRestService: BoardRestService
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
    @Operation(summary = "게시글 목록", description = "특정 게시판의 게시글을 불러오는 API")
    fun list(
        @AuthenticationPrincipal rcUser: RcUserJwtClaims,
        @PathVariable("bbs_path") bbsPath: String,
    ): Flux<RestResponseVO<RepBoardListVO>> {
        return boardRestService.list(rcUser, bbsPath)
    }

    // 2
    @GetMapping("/{bbs_path}/{board_uid}")
    @Operation(summary = "게시글 상세", description = "특정 게시글을 상세하게 불러오는 API")
    fun detail(
        @AuthenticationPrincipal rcUser: RcUserJwtClaims,
        @PathVariable("bbs_path") bbsPath: String,
        @PathVariable("board_uid") uid: Long,
    ): Mono<RestResponseVO<RepBoardDetailVO>> {
        return boardRestService.detail(rcUser, bbsPath, uid)
    }

    // 3
    @PostMapping
    @Operation(summary = "게시글 쓰기", description = "특정 게시판에 글을 등록하는 API")
    fun insert(
        @AuthenticationPrincipal rcUser: RcUserJwtClaims,
        @RequestBody reqBoardInsertVO: ReqBoardInsertVO
    ): Mono<RestResponseVO<Void>> {
        return boardRestService.insert(rcUser, reqBoardInsertVO)
    }

    // 4
    @PatchMapping
    @Operation(summary = "게시글 수정", description = "특정 게시글을 수정하는 API")
    fun update(
        @AuthenticationPrincipal rcUser: RcUserJwtClaims,
        @RequestBody reqBoardUpdateDTO: ReqBoardUpdateDTO,
    ): Mono<RestResponseVO<Void>> {
        return boardRestService.update(rcUser, reqBoardUpdateDTO)
    }

    // 5
    @DeleteMapping("/{bbs_path}/{board_uid}")
    @Operation(summary = "게시글 삭제", description = "특정 게시글을 삭제하는 API")
    fun delete(
        @AuthenticationPrincipal rcUser: RcUserJwtClaims,
        @PathVariable("bbs_path") bbsPath: String,
        @PathVariable("board_uid") boardUid: Long,
    ): Mono<RestResponseVO<Void>> {
        return boardRestService.delete(rcUser, ReqBoardDeleteVO(bbsPath, boardUid))
    }

    // 6
    @GetMapping("/{bbs_path}/{board_uid}/file")
    @Operation(summary = "게시글 첨부파일 목록", description = "특정 게시글의 첨부파일 목록을 불러오는 API")
    fun getBoardFileList(
        @AuthenticationPrincipal rcUser: RcUserJwtClaims,
        @PathVariable("bbs_path") bbsPath: String,
        @PathVariable("board_uid") boardUid: Long,
    ): Flux<RestResponseVO<RepBoardFileListVO>> {
        return boardRestService.getBoardFileList(rcUser, bbsPath, boardUid)
    }

    // 7
    @PostMapping("/{bbs_path}/{board_uid}/file")
    @Operation(summary = "게시글 첨부파일 등록", description = "특정 게시글의 첨부파일을 등록하는 API")
    fun insertBoardFile(
        @AuthenticationPrincipal rcUser: RcUserJwtClaims,
        @PathVariable("bbs_path") bbsPath: String,
        @PathVariable("board_uid") boardUid: Long,
        @RequestBody reqBoardInsertFileVO: List<ReqBoardFileInsertVO>,
    ): Flux<RestResponseVO<RepBoardFileInsertVO>> {
        return boardRestService.insertBoardFile(rcUser, bbsPath, boardUid, reqBoardInsertFileVO)
    }

    // 8
    @DeleteMapping("/{bbs_path}/{board_uid}/file")
    @Operation(summary = "게시글 첨부파일 삭제", description = "특정 게시글의 첨부파일을 삭제하는 API")
    fun deleteBoardFile(
        @AuthenticationPrincipal rcUser: RcUserJwtClaims,
        @PathVariable("bbs_path") bbsPath: String,
        @PathVariable("board_uid") boardUid: Long,
        @RequestBody boardFileUid: List<ReqBoardFileDeleteVO>,
    ): Flux<RestResponseVO<RepBoardFileDeleteVO>> {
        return boardRestService.deleteBoardFile(rcUser, bbsPath, boardUid, boardFileUid)
    }


}