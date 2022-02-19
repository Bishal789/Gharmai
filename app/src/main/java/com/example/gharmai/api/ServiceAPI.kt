package com.example.gharmai.api

import com.example.gharmai.entity.ServiceEntity
import com.example.gharmai.response.DeleteResponse
import com.example.gharmai.response.ServiceResponse
import com.example.gharmai.response.UserResponse
import retrofit2.Response
import retrofit2.http.*

interface ServiceAPI {

    //user API
    //routes
    @POST("service/insert")
    //suspend function to work with coroutines
    suspend fun registerService(
        //send user objects of type User class
        @Body user: ServiceEntity
    ): Response<ServiceResponse>

    @GET("service/showall")
    suspend fun getAllServiceAPI(
        @Header("Authorization")token: String
    ): Response<ServiceResponse>

    @DELETE("admin/service/delete/{id}")
    suspend fun deleteService(
        @Header("Authorization") token: String,
        @Path("id") id: String
    ): Response<DeleteResponse>

    @FormUrlEncoded
    @POST("service/subscribe/{id}")
    suspend fun bookService(
        @Header("Authorization") token: String,
        @Path("id")id: String,
        @Field("serviceID") serviceID: String
    ):Response<ServiceResponse>

    @GET("user/getCart")
    suspend fun getBooking(
        @Header("Authorization")token: String
    ): Response<ServiceResponse>
}