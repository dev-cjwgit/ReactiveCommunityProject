package com.devcjw.reactivecommunity.board.dao

import com.devcjw.reactivecommunity.board.model.entity.BoardInsertDTO
import com.devcjw.reactivecommunity.board.model.entity.BoardSelectDetailVO
import com.devcjw.reactivecommunity.board.model.entity.BoardSelectListVO
import com.devcjw.reactivecommunity.board.model.entity.BoardUpdateDTO
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono


interface BoardDAO {
    fun isBbsBoard(bbsUid: Short): Mono<Boolean>

    fun selectBoardList(): Flux<BoardSelectListVO>

    fun selectBoardDetail(postUid: Long): Mono<BoardSelectDetailVO>

    fun insertPost(boardInsertDTO: BoardInsertDTO): Mono<Void>

    fun updatePost(boardUpdateDTO: BoardUpdateDTO): Mono<Void>

    fun deletePost(postUid: Long): Mono<Void>

}