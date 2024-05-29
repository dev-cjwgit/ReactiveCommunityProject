package com.devcjw.reactivecommunity.board.model.entity

data class BoardUpdateDTO(
    val uid: Long,
    val title: String,
    val contents: String,
)
