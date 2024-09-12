package com.devcjw.reactivecommunity.board.model.entity

import java.time.ZonedDateTime

data class OutBoardSelectListVO(
        val uid: Long,
        val title: String,
        val createdUserNickname: String,
        val hit: Int,
        val createdUtcAt: ZonedDateTime,
        val updatedUtcAt: ZonedDateTime,
)
