package com.devcjw.reactivecommunity.board.dao

import com.devcjw.reactivecommunity.board.model.entity.BoardInsertDTO
import com.devcjw.reactivecommunity.board.model.entity.BoardSelectDetailVO
import com.devcjw.reactivecommunity.board.model.entity.BoardSelectListVO
import com.devcjw.reactivecommunity.board.model.entity.BoardUpdateDTO
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono


interface BoardDAO {
    fun isBbsBoard(bbsUid: Short): Mono<Boolean>

    fun selectList(): Flux<BoardSelectListVO>

    fun selectDetail(postUid: Long): Mono<BoardSelectDetailVO>

    fun insert(boardInsertDTO: BoardInsertDTO): Mono<Void>

    fun update(boardUpdateDTO: BoardUpdateDTO): Mono<Void>

    fun delete(postUid: Long): Mono<Void>

}