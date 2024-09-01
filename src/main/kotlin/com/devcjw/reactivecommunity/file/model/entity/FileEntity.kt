package com.devcjw.reactivecommunity.file.model.entity

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table

@Table("RC_FILE")
data class FileEntity(
    @Id val uid: String,
    val path: String,
    val size: Int,
    val name: String,
    val md5: String
) {
}