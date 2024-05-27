package com.devcjw.reactivecommunity.board.model.domain

data class BoardReqUpdateDTO(
    val boardUid: Long,
    val title: String,
    val contents: String,
)
