package com.example.ytastyle_app.models

data class Users(
    val id_user: Int,
    val number_id: Int,
    val name_user: String,
    val email: String,
    val password_user: String,
    val id_role_user: Int
)
