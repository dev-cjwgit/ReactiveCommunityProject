package com.devcjw.reactivecommunity.board.model.domain

data class CommentReqInsertDTO(
    val boardUid: Long,
    val contents: String,
)