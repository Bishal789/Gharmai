package com.example.gharmai.repository

import com.example.gharmai.api.ApiRequest
import com.example.gharmai.api.ServiceBuilder
import com.example.gharmai.api.UserAPI
import com.example.gharmai.entity.UserEntity
import com.example.gharmai.response.UserResponse

class UserRepository : ApiRequest(){

    private val userApi =
        ServiceBuilder.buildService(UserAPI::class.java)

    //Register user
    suspend fun registerUser(user: UserEntity): UserResponse {
        return apiRequest {
            userApi.registerUser(user)
        }
    }
    // Login user
    suspend fun login(email: String, password: String): UserResponse {
        return apiRequest {
            userApi.loginUser(email, password)
        }
    }
}