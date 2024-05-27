package com.devcjw.reactivecommunity.board.model.entity

data class CommentInsertDTO(
    val boardUid: Long,
    val userUid: String,
    val contents: String,
)