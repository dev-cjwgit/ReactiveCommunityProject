package com.devcjw.reactivecommunity.board.model.entity

import java.time.LocalDateTime

data class OutBoardSelectDetailVO(
        val uid: Long,
        val title: String,
        val contents: String,
        val writerNickname: String,
        val hit: Int,
        val createdUtcAt: LocalDateTime,
        val updatedUtcAt: LocalDateTime,
)