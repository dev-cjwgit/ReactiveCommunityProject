package com.devcjw.reactivecommunity.board.dao

import com.devcjw.reactivecommunity.board.model.entity.*
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono


interface BoardDAO {
    fun isBbsUid(uid: Short): Mono<Boolean>
    fun isBbsPath(path: String): Mono<Boolean>
    fun isBoardUid(uid: Long): Mono<Boolean>
    fun isWriterBoard(boardUid: Long, writerUid: String): Mono<Boolean>

    fun selectList(bbsPath: String): Flux<BoardSelectListVO>

    fun selectDetail(boardUid: Long): Mono<BoardSelectDetailVO>

    fun insert(boardInsertDTO: BoardInsertDTO): Mono<Void>

    fun update(boardUpdateDTO: BoardUpdateDTO): Mono<Void>

    fun delete(boardUid: Long): Mono<Void>

    fun insertFile(boardInsertFileDTO: BoardInsertFileDTO): Mono<Void>

}