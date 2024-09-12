package com.devcjw.reactivecommunity.board.model.domain

import java.time.ZonedDateTime

data class RepBoardDetailVO(
        val uid: Long,
        val title: String,
        val contents: String,
        val writerNickname: String,
        val hit: Int,
        val createdUtcAt: ZonedDateTime,
        val updatedUtcAt: ZonedDateTime,
)
