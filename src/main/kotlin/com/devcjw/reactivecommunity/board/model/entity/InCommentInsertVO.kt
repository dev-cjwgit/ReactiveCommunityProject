package com.devcjw.reactivecommunity.board.model.entity

data class InCommentInsertVO(
        val boardUid: Long,
        val createdUserUid: String,
        val contents: String,
)