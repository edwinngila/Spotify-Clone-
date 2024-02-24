package com.example.classwork.data

data class MusicItems(
    var musicImg: String?= null,
    var musicUrl:String? = null,
    var name:String? = null,
){
    fun toMap()= mapOf(
        "musicImg" to musicImg,
        "musicUrl" to musicUrl,
        "name" to name,
    )
}
