package com.example.gharmai.repository

import com.example.gharmai.api.ApiRequest
import com.example.gharmai.api.ServiceBuilder
import com.example.gharmai.api.UserAPI
import com.example.gharmai.api.WorkerAPI
import com.example.gharmai.entity.UserEntity
import com.example.gharmai.entity.WorkerEntity
import com.example.gharmai.response.UserResponse
import com.example.gharmai.response.WorkerResponse
import okhttp3.MultipartBody

class WorkerRepository : ApiRequest(){

    private val workerApi =
        ServiceBuilder.buildService(WorkerAPI::class.java)

    //Register worker
    suspend fun registerWorker(worker: WorkerEntity): WorkerResponse {
        return apiRequest {
            workerApi.registerUser(worker)
        }
    }
    // Login worker
    suspend fun loginWorker(email: String, password: String): WorkerResponse {
        return apiRequest {
            workerApi.loginWorker(email, password)
        }
    }
//    suspend fun getCurrentUserAPI(id: String): UserResponse {
//        return apiRequest {
//            userApi.getAllUserAPI(ServiceBuilder.token!!, id)
//        }
//    }
//    suspend fun editUser(id: String,username: String, email: String,address: String,phone:String): UserResponse {
//        return apiRequest {
//            userApi.editUser(ServiceBuilder.token!!,id, username,  email,address,phone,)
//        }
//    }
//    suspend fun updateuser(id:String, data: UserEntity): UserResponse {
//        return apiRequest {
//            userApi.updateuser(ServiceBuilder.token!!, id, data)
//        }
//    }
//    suspend fun updateimage(id:String,body: MultipartBody.Part): UserResponse {
//        return apiRequest {
//            userApi.updateimage(ServiceBuilder.token!!, id, body)
//        }
//    }
//    suspend fun deleteuser(id:String): UserResponse {
//        return apiRequest {
//            userApi.deleteuser(ServiceBuilder.token!!, id)
//        }
//    }suspend fun getUserDetails(id:String): UserResponse {
//        return apiRequest {
//            userApi.getAllUserAPI(ServiceBuilder.token!!, id)
//        }
//    }
}