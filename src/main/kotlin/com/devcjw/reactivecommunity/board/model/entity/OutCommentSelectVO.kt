package com.devcjw.reactivecommunity.board.model.entity

import java.time.ZonedDateTime

data class OutCommentSelectVO(
        val uid: Long,
        val contents: String,
        val createdUserUid: String,
        val createdUtcAt: ZonedDateTime,
        val updatedUtcAt: ZonedDateTime,
)