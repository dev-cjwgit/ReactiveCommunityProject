package com.devcjw.reactivecommunity.board.controller

import com.devcjw.reactivecommunity.auth.model.domain.RcUserJwtClaims
import com.devcjw.reactivecommunity.board.model.domain.RepBbsListVO
import com.devcjw.reactivecommunity.board.service.BbsService
import com.devcjw.reactivecommunity.common.model.RestResponseVO
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import lombok.RequiredArgsConstructor
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Flux

@RestController
@RequestMapping("/bbs")
@RequiredArgsConstructor
@Tag(name = "게시판 컨트롤러", description = "게시판을 담당하는 컨트롤러")
class BbsController(
    private val bbsService: BbsService
) {
    @GetMapping
    @Operation(summary = "게시판 목록 불러오기", description = "게시판 목록을 불러오는 API")
    fun list(
        @AuthenticationPrincipal rcUser: RcUserJwtClaims
    ): Flux<RestResponseVO<RepBbsListVO>> {
        return bbsService.list(rcUser)
    }
}