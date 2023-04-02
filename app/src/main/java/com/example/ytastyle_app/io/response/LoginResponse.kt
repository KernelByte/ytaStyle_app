package com.example.ytastyle_app.io.response

import com.example.ytastyle_app.model.Users

data class LoginResponse(
    val user: Users,
    val success: Boolean
    //val jwt: String
)
