package com.devcjw.reactivecommunity.board.model.entity

import java.time.LocalDateTime

data class OutBbsListVO(
    val uid: Long,
    val path: String,
    val title: String,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime
)
