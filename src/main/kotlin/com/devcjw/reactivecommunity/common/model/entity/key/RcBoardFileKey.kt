package com.devcjw.reactivecommunity.common.model.entity.key

import java.io.Serializable


data class RcBoardFileKey(
    val boardUid: Long,
    val fileUid: String
) : Serializable
