package com.devcjw.reactivecommunity.board.model.entity

import java.time.LocalDateTime

data class OutCommentSelectVO(
    val uid: Long,
    val contents: String,
    val writerUid: String,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime,
)