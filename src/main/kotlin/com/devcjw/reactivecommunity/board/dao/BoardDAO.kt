package com.devcjw.reactivecommunity.board.dao

import com.devcjw.reactivecommunity.board.model.entity.*
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono


interface BoardDAO {
    fun isBbsUid(uid: Short): Mono<Boolean>
    fun isBbsPath(path: String): Mono<Boolean>
    fun isBoardUid(uid: Long): Mono<Boolean>
    fun isWriterBoard(boardUid: Long, writerUid: String): Mono<Boolean>
    fun isExistBoardFile(boardUid: Long, fileUid: String): Mono<Boolean>

    fun selectList(bbsPath: String): Flux<OutBoardSelectListVO>

    fun selectDetail(boardUid: Long): Mono<OutBoardSelectDetailVO>

    fun insert(inBoardInsertVO: InBoardInsertVO): Mono<Long>

    fun update(inBoardUpdateVO: InBoardUpdateVO): Mono<Void>

    fun delete(boardUid: Long): Mono<Void>

    fun insertFile(inBoardInsertFileVO: InBoardInsertFileVO): Mono<Void>

    fun deleteFile(boardUid: Long, fileUid: String): Mono<Void>

    fun selectFileList(boardUid: Long): Flux<OutBoardFileListVO>
}