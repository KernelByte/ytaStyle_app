package com.kernelbyte.ytastyle_app.io.response

import com.kernelbyte.ytastyle_app.model.Users

data class LoginResponse(
    val success: Boolean,
    val user: Users,
    val jwt: String
)
