package com.devcjw.reactivecommunity.board.model.entity

import java.time.LocalDateTime

data class CommentSelectVO(
    val uid: Long,
    val contents: String,
    val writerUid: Long,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime,
)