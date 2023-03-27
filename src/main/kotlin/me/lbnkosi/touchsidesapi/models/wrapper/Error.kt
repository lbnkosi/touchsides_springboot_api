package me.lbnkosi.touchsidesapi.models.wrapper

data class Error(
    var error: String? = "",
    var errorCode: Int? = 0,
    var message: String? = ""
)
