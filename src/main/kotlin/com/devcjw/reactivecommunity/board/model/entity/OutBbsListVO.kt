package com.devcjw.reactivecommunity.board.model.entity

import java.time.ZonedDateTime

data class OutBbsListVO(
        val uid: Long,
        val path: String,
        val name: String,
        val createdUtcAt: ZonedDateTime,
        val updatedUtcAt: ZonedDateTime
)
