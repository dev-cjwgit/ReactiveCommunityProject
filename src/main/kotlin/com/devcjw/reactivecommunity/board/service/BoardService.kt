package com.devcjw.reactivecommunity.board.service

import com.devcjw.reactivecommunity.auth.model.RcUserJwtClaims
import com.devcjw.reactivecommunity.board.model.domain.*
import com.devcjw.reactivecommunity.common.model.RestResponseVO
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono


interface BoardService {
    fun list(rcUser: RcUserJwtClaims, bbsPath: String): Flux<RestResponseVO<RepBoardListVO>>
    fun detail(rcUser: RcUserJwtClaims, bbsPath: String, uid: Long): Mono<RestResponseVO<RepBoardDetailVO>>
    fun insert(rcUser: RcUserJwtClaims, reqBoardInsertVO: ReqBoardInsertVO): Mono<RestResponseVO<Void>>
    fun update(rcUser: RcUserJwtClaims, reqBoardUpdateDTO: ReqBoardUpdateDTO): Mono<RestResponseVO<Void>>
    fun delete(rcUser: RcUserJwtClaims, reqBoardDeleteVO: ReqBoardDeleteVO): Mono<RestResponseVO<Void>>
    fun getBoardFileList(rcUser: RcUserJwtClaims, bbsPath: String, boardUid: Long): Flux<RestResponseVO<RepBoardFileListVO>>
    fun insertBoardFile(rcUser: RcUserJwtClaims, bbsPath: String, boardUid: Long, reqBoardInsertFileVO: List<ReqBoardFileInsertVO>): Flux<RestResponseVO<RepBoardFileInsertVO>>
    fun deleteBoardFile(rcUser: RcUserJwtClaims, bbsPath: String, boardUid: Long, boardFileUid: List<ReqBoardFileDeleteVO>): Flux<RestResponseVO<RepBoardFileDeleteVO>>
}