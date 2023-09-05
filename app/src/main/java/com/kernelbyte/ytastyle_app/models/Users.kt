package com.kernelbyte.ytastyle_app.models

data class Users(
    val idUser: Int,
    val nameUser: String,
    val mailUser: String,
    val passwordUser: String,
    val idRoleUser: Int,
    val tokenUser: String,
    val codeReference: String,
    //val profilePicture:
    val idBusinessUser: Int
)
