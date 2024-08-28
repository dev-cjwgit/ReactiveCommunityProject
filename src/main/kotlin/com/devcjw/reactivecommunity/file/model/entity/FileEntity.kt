package com.devcjw.reactivecommunity.file.model.entity

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table
import java.time.LocalDateTime

@Table("RC_FILE")
data class FileEntity(
        @Id val uid: String,
        val path: String,
        val size: Int,
        val name: String,
        val md5: String,
        val createdAt: LocalDateTime,
        val updatedAt: LocalDateTime
) {
}