package com.example.classwork.data

data class UserMusic (
    var id : String?= null,
    var uid: String? = null,
    var name: String? = null,
    var description: String? = null,
    var musicImg: String? = null,
    var musicUrl: String? = null,
){
    fun toMap()= mapOf(
        "id " to id ,
        "uid" to uid,
        "name" to name,
        "description" to description,
        "musicImg" to musicImg,
        "musicUrl" to musicUrl,
    )
}