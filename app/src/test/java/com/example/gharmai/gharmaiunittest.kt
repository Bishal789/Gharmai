package com.example.gharmai

import com.example.gharmai.api.ServiceBuilder
import com.example.gharmai.entity.UserEntity
import com.example.gharmai.repository.UserRepository
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test

class gharmaiunittest {
    private lateinit var Userrepo: UserRepository

//    @Test
//    fun testLogin() = runBlocking {
//        Userrepo = UserRepository()
//        val username = "Rajesh"
//        val password = "123"
//        val res = Userrepo.login(username, password)
//        val expected = true
//        val actual = res.success
//
//        ServiceBuilder.token = res.token
//        Assert.assertEquals(expected, actual)
//    }
//
    @Test
    fun testregister() = runBlocking {
        Userrepo = UserRepository()

        val data= UserEntity(
            username = "Bhuwan",
            emailUser = "bhuwan123@gmail.com" ,
            passwordUser = "123"
        )
<<<<<<< HEAD
        val res = Userrepo.registerUser(user=data)
=======
        val res = Userrepo.registerUser(data)
>>>>>>> 0ee3650d9eb09ba1d8dfb2e16c905702f92daf95
        val expected = true
        val actual = res.success

        ServiceBuilder.token = res.token
        Assert.assertEquals(expected, actual)

    }
}