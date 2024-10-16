package com.devcjw.reactivecommunity.common.model.entity.key

import java.io.Serializable


data class RcRoleResourceKey(
    val roleUid: Byte,
    val resourceUid: Long
) : Serializable
