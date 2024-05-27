package com.devcjw.reactivecommunity.board.model.domain

data class BoardReqUpdateDTO(
    val postUid: Long,
    val title: String,
    val contents: String,
)
