package com.example.gharmai.repository

import com.example.gharmai.api.ApiRequest
import com.example.gharmai.api.ServiceBuilder
import com.example.gharmai.api.UserAPI
import com.example.gharmai.entity.UserEntity
import com.example.gharmai.response.UserResponse
import okhttp3.MultipartBody

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
    suspend fun getcurrentUserAPI(id: String): UserResponse {
        return apiRequest {
            userApi.getAllUserAPI(ServiceBuilder.token!!, id)
        }
    }
    suspend fun editUser(id: String,username: String, email: String,address: String,phone:String): UserResponse {
        return apiRequest {
            userApi.editUser(ServiceBuilder.token!!,id, username,  email,address,phone,)
        }
    }
    suspend fun updateuser(id:String, data: UserEntity): UserResponse {
        return apiRequest {
            userApi.updateuser(ServiceBuilder.token!!, id, data)
        }
    }
    suspend fun updateimage(id:String,body: MultipartBody.Part): UserResponse {
        return apiRequest {
            userApi.updateimage(ServiceBuilder.token!!, id, body)
        }
    }
    suspend fun deleteuser(id:String): UserResponse {
        return apiRequest {
            userApi.deleteuser(ServiceBuilder.token!!, id)
        }
    }
}