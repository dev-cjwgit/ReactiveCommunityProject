package com.devcjw.reactivecommunity.board.model.entity

import java.time.LocalDateTime

data class OutBbsListVO(
        val uid: Long,
        val path: String,
        val title: String,
        val createdUtcAt: LocalDateTime,
        val updatedUtcAt: LocalDateTime
)
