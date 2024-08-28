package com.devcjw.reactivecommunity.board.model.domain

data class ReqCommentInsertDTO(
        val boardUid: Long,
        val contents: String,
)