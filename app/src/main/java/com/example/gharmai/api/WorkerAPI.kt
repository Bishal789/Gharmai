package com.example.gharmai.api

import com.example.gharmai.entity.UserEntity
import com.example.gharmai.entity.WorkerEntity
import com.example.gharmai.response.UserResponse
import com.example.gharmai.response.WorkerResponse
import retrofit2.Response
import retrofit2.http.*

interface WorkerAPI {

    //user API
    //routes
    @POST("worker/register")
    suspend fun registerUser(
        @Body worker: WorkerEntity
    ): Response<WorkerResponse>


    @FormUrlEncoded
    @POST("worker/login")
    suspend fun loginWorker(
        //send parameters
//        @Body user: UserEntity
        @Field("workerEmail") email: String,
        @Field("workerPassword") password: String
    ): Response<WorkerResponse>

    @GET("worker/profile/show/{id}")
    suspend fun getWorkerProfile(
        @Header("Authorization")token: String,
        @Path("id")  id: String
    ): Response<WorkerResponse>
//
//    @FormUrlEncoded
//    @PUT("user/update/{id}")
//    suspend fun editUser(
//        @Header("Authorization") token: String,
//        @Path("id") id:String,
//        @Field("username") username:String,
//        @Field("email") email:String,
//        @Field("address") address:String,
//        @Field("phone") phone:String,
//    ): Response<UserResponse>

    @PUT("worker/profile/update/{id}")
    suspend fun updateWorker(
        @Header("Authorization") token: String,
        @Path("id") id: String,
        @Body data : WorkerEntity
    ): Response<WorkerResponse>
//
//
////    @Multipart
////    @PUT("profile/update/{id}")
////    suspend fun updateimage(
////        @Header("Authorization") token: String,
////        @Path("id") id: String,
////        @Part file: MultipartBody.Part
////    ): Response<UserResponse>
//
//    @Multipart
//    @PUT("profile/image/update/{id}")
//    suspend fun updateimage(
//        @Header("Authorization") token: String,
//        @Path("id") id: String,
//        @Part file: MultipartBody.Part
//    ): Response<UserResponse>
//
//    @DELETE("profile/delete/{id}")
//    suspend fun deleteuser(
//        @Header("Authorization") token: String,
//        @Path("id") id: String,
//
//        ):Response<UserResponse>
}