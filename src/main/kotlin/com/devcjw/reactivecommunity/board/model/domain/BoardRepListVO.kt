package com.devcjw.reactivecommunity.board.model.domain

import java.time.LocalDateTime

data class BoardRepListVO(
    val uid: Long,
    val title: String,
    val writerNickname: String,
    val hit: Int,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime,
)
