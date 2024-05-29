package com.devcjw.reactivecommunity.board.model.domain

data class BoardReqUpdateDTO(
        val uid: Long,
        val bbsPath: String,
        val title: String,
        val contents: String,
)
