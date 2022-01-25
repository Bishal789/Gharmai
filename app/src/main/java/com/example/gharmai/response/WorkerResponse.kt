package com.example.gharmai.response

import com.example.gharmai.entity.WorkerEntity

//it receives response from api for sign in/up


data class WorkerResponse(
    val success:Boolean? = null,
    val token: String? =null,
    val data: WorkerEntity? = null,
//    val data: MutableList<UserEntity>? = null,
    val userId: String? = null,
) {
}