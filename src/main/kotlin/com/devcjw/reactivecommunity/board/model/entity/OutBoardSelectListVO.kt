package com.devcjw.reactivecommunity.board.model.entity

import java.time.LocalDateTime

data class OutBoardSelectListVO(
    val uid: Long,
    val title: String,
    val writerNickname: String,
    val hit: Int,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime,
)
