package com.example.gharmai.response

import com.example.gharmai.entity.ServiceEntity

data class ServiceResponse(
    val success:Boolean? = null,
    val token: String? =null,
    val data: ServiceEntity? = null,
    val userId: String? = null,
) {
}