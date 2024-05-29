package com.devcjw.reactivecommunity.board.model.domain

import org.springframework.web.bind.annotation.PathVariable

data class BoardReqDeleteDTO(
        val bbsPath: String,
        val uid: Long,
)
