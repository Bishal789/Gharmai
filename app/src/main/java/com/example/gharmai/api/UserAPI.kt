package com.example.gharmai.api

import com.example.gharmai.entity.UserEntity
import com.example.gharmai.response.UserResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface UserAPI {

    //user API
    //routes
    @POST("user/register")
    //suspend function to work with coroutines
    suspend fun registerUser(
        //send user objects of type User class
        @Body user: UserEntity
    ): Response<UserResponse>


    @FormUrlEncoded
    @POST("user/login")
    suspend fun loginUser(
        //send parameters
        @Field("email") email: String,
        @Field("password") password: String
    ): Response<UserResponse>
}