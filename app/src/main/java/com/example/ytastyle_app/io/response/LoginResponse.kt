package com.example.ytastyle_app.io.response

import com.example.ytastyle_app.models.Users

data class LoginResponse(
    val success: Boolean,
    val user: Users,
    val jwt: String
)
