package com.devcjw.reactivecommunity.board.model.entity

import java.time.ZonedDateTime

data class OutBoardSelectDetailVO(
        val uid: Long,
        val title: String,
        val contents: String,
        val writerNickname: String,
        val hit: Int,
        val createdUtcAt: ZonedDateTime,
        val updatedUtcAt: ZonedDateTime,
)