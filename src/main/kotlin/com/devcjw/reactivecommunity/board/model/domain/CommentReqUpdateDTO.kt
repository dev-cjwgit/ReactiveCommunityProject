package com.devcjw.reactivecommunity.board.model.domain

data class CommentReqUpdateDTO(
    val boardUid: Long,
    val contents: String,
)
