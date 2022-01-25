package com.example.gharmai.entity

import android.os.Parcel
import android.os.Parcelable

data class WorkerEntity(

    val _id: String? = null,
    val workerName: String?= null,
    val workerEmail: String?= null,
    val workerPassword: String?= null,
    val workerProfile_pic: String?= null,
    val workerDateOfBirth: String?= null,
    val workerAddress: String?= null,
    var workerContactNo: String?=null,
    var workerGender: String?=null
)