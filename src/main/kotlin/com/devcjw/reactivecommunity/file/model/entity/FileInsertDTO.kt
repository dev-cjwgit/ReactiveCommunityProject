package com.devcjw.reactivecommunity.file.model.entity

data class FileInsertDTO(
    val uid: String,
    val path: String,
    val name: String,
    val size: Int,
    val md5: String,
)
