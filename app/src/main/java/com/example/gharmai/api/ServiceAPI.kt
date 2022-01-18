package com.example.gharmai.api

import com.example.gharmai.entity.ServiceEntity
import com.example.gharmai.response.ServiceResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface ServiceAPI {

    //user API
    //routes
    @POST("service/insert")
    //suspend function to work with coroutines
    suspend fun registerService(
        //send user objects of type User class
        @Body user: ServiceEntity
    ): Response<ServiceResponse>
}