package kr.co.kworks.daelongdaelong.vo

data class DaeLongVo(
    val title: String? = null,
    val content: String? = null,
    val review: String? = null,
    val image: String? = null,
    val latitude: Double? = null,
    val longitude: Double? = null,
    var imageList: MutableList<String>? = null
)