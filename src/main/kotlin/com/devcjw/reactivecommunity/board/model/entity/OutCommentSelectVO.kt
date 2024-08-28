package com.devcjw.reactivecommunity.board.model.entity

import java.time.LocalDateTime

data class OutCommentSelectVO(
        val uid: Long,
        val contents: String,
        val createdUserUid: String,
        val createdUtcAt: LocalDateTime,
        val updatedUtcAt: LocalDateTime,
)