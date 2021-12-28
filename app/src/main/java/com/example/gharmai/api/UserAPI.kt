package com.example.gharmai.api

import com.example.gharmai.entity.UserEntity
import com.example.gharmai.response.UserResponse
import okhttp3.MultipartBody
import retrofit2.Response
import retrofit2.http.*

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
//        @Body user: UserEntity
        @Field("emailUser") email: String,
        @Field("passwordUser") password: String
    ): Response<UserResponse>

    @GET("profile/single/{id}")
    suspend fun getAllUserAPI(
        @Header("Authorization")token: String,
        @Path("id")  id: String
    ): Response<UserResponse>

    @FormUrlEncoded
    @PUT("user/update/{id}")
    suspend fun editUser(
        @Header("Authorization") token: String,
        @Path("id") id:String,
        @Field("username") username:String,
        @Field("email") email:String,
        @Field("address") address:String,
        @Field("phone") phone:String,
    ): Response<UserResponse>
    @PUT("profile/update/{id}")
    suspend fun updateuser(
        @Header("Authorization") token: String,
        @Path("id") id: String,
        @Body data : UserEntity
    ): Response<UserResponse>


    @Multipart
    @PUT("profile/image/update/{id}")
    suspend fun updateimage(
        @Header("Authorization") token: String,
        @Path("id") id: String,
        @Part file: MultipartBody.Part
    ): Response<UserResponse>

    @DELETE("/profile/delete/{id}")
    suspend fun deleteuser(
        @Header("Authorization") token: String,
        @Path("id") id: String,

        ):Response<UserResponse>
}