package com.devcjw.reactivecommunity.board.model.domain

data class BoardReqInsertDTO(
    val bbsUid: Short,
    val title: String,
    val contents: String,
)
