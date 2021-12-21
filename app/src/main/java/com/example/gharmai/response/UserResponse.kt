package com.example.gharmai.response

//it receives response from api for sign in/up


data class UserResponse(
    val success:Boolean? = null,
    val token: String? =null
) {
}