package com.kernelbyte.ytastyle_app.models

import com.kernelbyte.ytastyle_app.models.Users

data class LoginResponse(
    val success: Boolean,
    val user: Users,
    val jwt: String
)
