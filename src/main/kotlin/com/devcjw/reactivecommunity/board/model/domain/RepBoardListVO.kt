package com.devcjw.reactivecommunity.board.model.domain

import java.time.ZonedDateTime

data class RepBoardListVO(
        val uid: Long,
        val title: String,
        val writerNickname: String,
        val hit: Int,
        val createdUtcAt: ZonedDateTime,
        val updatedUtcAt: ZonedDateTime,
)
