package com.example.gharmai.repository

import android.util.Log
import com.example.gharmai.api.ApiRequest
import com.example.gharmai.api.ServiceAPI
import com.example.gharmai.api.ServiceBuilder
import com.example.gharmai.entity.ServiceEntity
import com.example.gharmai.response.DeleteResponse
import com.example.gharmai.response.ServiceResponse
import com.example.gharmai.response.UserResponse

class ServiceRepository: ApiRequest() {

    private val serviceAPI =
        ServiceBuilder.buildService(ServiceAPI::class.java)


    suspend fun registerUser(service: ServiceEntity): ServiceResponse {
        return apiRequest {
            serviceAPI.registerService(service)
        }
    }

    suspend fun getAllServiceAPI(): ServiceResponse {
        return apiRequest {
            serviceAPI.getAllServiceAPI(ServiceBuilder.token!!)
        }
    }
    suspend fun deleteService(id: String): DeleteResponse {
        return apiRequest {
            serviceAPI.deleteService(ServiceBuilder.token!!,id)
        }
    }
    suspend fun bookService( token: String, id: String, serviceID: String): ServiceResponse {
        return apiRequest {
            Log.d("bfafeag", serviceID)
            serviceAPI.bookService(token, id, serviceID)
        }
    }
    suspend fun getBooking(): ServiceResponse {
        return apiRequest {
            serviceAPI.getBooking(ServiceBuilder.token!!)
        }
    }
}