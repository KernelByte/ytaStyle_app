package com.kernelbyte.ytastyle_app.io.response

import com.kernelbyte.ytastyle_app.model.Users

data class LoginResponse(
    val `data`: Data,
    val success: Boolean
)

data class Data(
    val jwt: String,
    val user: User
)

data class User(
    val codeReference: Any,
    val idBusinessUser: Any,
    val idRoleUser: Any,
    val idUser: Int,
    val mailUser: String,
    val nameUser: String,
    val passwordUser: Any,
    val tokenUser: Any
)