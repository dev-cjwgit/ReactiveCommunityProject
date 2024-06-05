package com.devcjw.reactivecommunity.board.model.domain

data class ReqBoardInsertVO(
        val bbsUid: Short,
        val title: String,
        val contents: String,
        val files: List<ReqBoardInsertFileVO>?
)
