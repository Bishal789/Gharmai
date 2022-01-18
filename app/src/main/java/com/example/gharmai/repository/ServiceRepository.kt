package com.example.gharmai.repository

import com.example.gharmai.api.ApiRequest
import com.example.gharmai.api.ServiceAPI
import com.example.gharmai.api.ServiceBuilder
import com.example.gharmai.entity.ServiceEntity
import com.example.gharmai.response.ServiceResponse

class ServiceRepository: ApiRequest() {

    private val serviceAPI =
        ServiceBuilder.buildService(ServiceAPI::class.java)


    suspend fun registerUser(service: ServiceEntity): ServiceResponse {
        return apiRequest {
            serviceAPI.registerService(service)
        }
    }
}