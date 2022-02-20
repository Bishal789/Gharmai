package com.example.gharmai.response

import com.example.gharmai.entity.BookEntity
import com.example.gharmai.entity.ServiceBook
import com.example.gharmai.entity.ServiceEntity

data class ServiceResponse(
    val success:Boolean? = null,
    val token: String? =null,
    val bookedItem: BookEntity? =null,
    val data: MutableList<ServiceEntity>? = null,
    val userId: String? = null,
    val message: String?=null,

    ) {
}