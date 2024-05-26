package com.devcjw.reactivecommunity.common.model

data class RestResponseVO<T>(
    val result: Boolean = false,
    val data: T? = null,
    val message: String? = null,
)
