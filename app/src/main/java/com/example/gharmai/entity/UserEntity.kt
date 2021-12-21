package com.example.gharmai.entity

data class UserEntity(

    val _id: String = "",
    val username: String?,
    val addressUser: String?,
    val phoneUser: String?,
    val emailUser: String?,
    val passwordUser: String?,
    val genderUser: String?
) {
}