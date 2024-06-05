package com.devcjw.reactivecommunity.board.model.entity

data class InCommentInsertVO(
    val boardUid: Long,
    val userUid: String,
    val contents: String,
)