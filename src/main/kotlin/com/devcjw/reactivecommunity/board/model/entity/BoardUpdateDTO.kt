package com.devcjw.reactivecommunity.board.model.entity

data class BoardUpdateDTO(
    val uid: Long? = null,
    val bbsUid: Short,
    val title: String,
    val contents: String,
    val writerUid: Long,
)
