package com.devcjw.reactivecommunity.common.model.entity

import org.springframework.data.relational.core.mapping.Table
import java.time.ZonedDateTime

@Table("rc_board_file")
data class RcBoardFileEntity(
    val boardUid: Long,
    val fileUid: String,
    val fileName: String,
    val createdUtcAt: ZonedDateTime,
    val updatedUtcAt: ZonedDateTime
)
