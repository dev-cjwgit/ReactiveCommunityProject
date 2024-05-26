package com.devcjw.reactivecommunity.board.model.entity

data class BoardInsertDTO(
    val bbsUid: Short,
    val title: String,
    val contents: String,
    val writerUid: String,
)
