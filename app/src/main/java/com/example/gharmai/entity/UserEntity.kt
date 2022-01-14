package com.example.gharmai.entity

import android.os.Parcel
import android.os.Parcelable

data class UserEntity(

    val _id: String? = null,
    val username: String?= null,
    val addressUser: String?= null,
    val phoneUser: String?= null,
    val emailUser: String?= null,
    val passwordUser: String?= null,
    val genderUser: String?= null,
    var profile_pic: String?=null
):Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun describeContents(): Int {
//        TODO("Not yet implemented")
        return 0
    }

    override fun writeToParcel(dest: Parcel?, flags: Int) {
//        TODO("Not yet implemented")
    }

    companion object CREATOR : Parcelable.Creator<UserEntity> {
        override fun createFromParcel(parcel: Parcel): UserEntity {
            return UserEntity(parcel)
        }

        override fun newArray(size: Int): Array<UserEntity?> {
            return arrayOfNulls(size)
        }
    }
}