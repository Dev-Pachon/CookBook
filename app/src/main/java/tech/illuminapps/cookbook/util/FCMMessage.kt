package tech.illuminapps.cookbook.util

data class FCMMessage<T:Any>(
    var to: String = "",
    var data : T? = null
)