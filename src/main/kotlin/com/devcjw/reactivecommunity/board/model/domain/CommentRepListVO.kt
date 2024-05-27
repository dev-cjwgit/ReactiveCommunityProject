package com.devcjw.reactivecommunity.board.model.domain

import java.time.LocalDateTime

data class CommentRepListVO(
    val uid: Long,
    val contents: String,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime,
)
