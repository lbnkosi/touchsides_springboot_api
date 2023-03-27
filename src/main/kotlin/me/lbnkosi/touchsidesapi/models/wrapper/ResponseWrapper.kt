package me.lbnkosi.touchsidesapi.models.wrapper

data class ResponseWrapper(
    var status: String? = "",
    var error: Error? = Error(),
    var success: Success? = Success(),
    var result: Any? = Any()
)
