package com.devcjw.reactivecommunity.board.model.domain

import java.time.ZonedDateTime

data class RepCommentListVO(
        val uid: Long,
        val contents: String,
        val createdUtcAt: ZonedDateTime,
        val updatedUtcAt: ZonedDateTime,
)
