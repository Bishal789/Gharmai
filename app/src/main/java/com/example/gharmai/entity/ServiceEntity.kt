package com.example.gharmai.entity

import android.os.Parcel
import android.os.Parcelable

data class ServiceEntity(

    val _id: String?= null,
    val categoryID: String? = null,
    val serviceName: String?= null,
    val serviceDetails: String?= null,
    val serviceImage: String?= null,
    val servicePrice: String?= null,
    val serviceCategory: String?=null
): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(_id)
        parcel.writeString(categoryID)
        parcel.writeString(serviceName)
        parcel.writeString(serviceDetails)
        parcel.writeString(serviceImage)
        parcel.writeString(servicePrice)
        parcel.writeString(serviceCategory)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ServiceEntity> {
        override fun createFromParcel(parcel: Parcel): ServiceEntity {
            return ServiceEntity(parcel)
        }

        override fun newArray(size: Int): Array<ServiceEntity?> {
            return arrayOfNulls(size)
        }
    }

}