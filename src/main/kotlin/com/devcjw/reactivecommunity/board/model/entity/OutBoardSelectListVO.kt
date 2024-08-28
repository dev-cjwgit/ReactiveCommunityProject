package com.devcjw.reactivecommunity.board.model.entity

import java.time.LocalDateTime

data class OutBoardSelectListVO(
        val uid: Long,
        val title: String,
        val createdUserNickname: String,
        val hit: Int,
        val createdUtcAt: LocalDateTime,
        val updatedUtcAt: LocalDateTime,
)
