package com.devcjw.reactivecommunity.board.model.entity

data class InBoardInsertVO(
        val bbsUid: Short,
        val title: String,
        val contents: String,
        val createdUserUid: String,
)
