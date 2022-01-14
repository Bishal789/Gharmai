package com.example.gharmai.api

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object ServiceBuilder {

    //ip address for physical device
    //10.0.2.2 for emulator

    val BASE_URL = "http://10.0.2.2:90/"

//        private const val BASE_URL = "http://172.25.0.86:90/"
//        private const val BASE_URL = "http://192.168.0.119:90/"
    //    private const val BASE_URL = "http://192.168.137.68:90/"
//    private const val BASE_URL = "http://localhost:90/"
    var token: String? =null
    var userId: String?= null

    var okHttpClient = OkHttpClient.Builder()
        .connectTimeout(1, TimeUnit.MINUTES)
        .readTimeout(30, TimeUnit.SECONDS)
        .writeTimeout(15, TimeUnit.SECONDS)
        .build()

    //create retrofit instance
    private val retrofitBuilder = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttpClient)
        .build()

    //generic function
    fun <A> buildService(serviceType: Class<A>):A{
        return retrofitBuilder.create(serviceType)
    }
}