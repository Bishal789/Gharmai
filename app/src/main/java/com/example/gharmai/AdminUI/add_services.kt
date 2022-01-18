package com.example.gharmai.AdminUI

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.gharmai.R

import com.example.gharmai.entity.ServiceEntity

import com.example.gharmai.repository.ServiceRepository

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class add_services : AppCompatActivity() {

    private lateinit var addServiceName: EditText
    private lateinit var addServicePrice: EditText
    private lateinit var addServiceAbout: EditText
    private lateinit var addServiceButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_services)

        addServiceAbout = findViewById(R.id.ADMIN_ETABOUT_SERVICE)
        addServiceName = findViewById(R.id.ADMIN_ETSERVICENAME)
        addServicePrice = findViewById(R.id.ADMIN_ETSERVICE_PRICE)
        addServiceButton = findViewById(R.id.ADMIN_ADD_SERVICE)

        addServiceButton.setOnClickListener {
            serviceAdd()
//            Toast.makeText(this, "Service Added", Toast.LENGTH_SHORT).show()
        }
    }
    private fun serviceAdd(){
        val serviceName = addServiceName.text.toString()
        val servicePrice = addServicePrice.text.toString()
        val serviceAbout = addServiceAbout.text.toString()

        if (isValid()){
            val user = ServiceEntity(
                serviceName = serviceName,
                servicePrice = servicePrice,
                serviceDetails = serviceAbout
                )

            CoroutineScope(Dispatchers.IO).launch {
                //StudentDB(this@UserRegister).getUserDAO().registerUser(user)
                try{
                    val serviceRepository = ServiceRepository()
                    val serviceResponse = serviceRepository.registerUser(user)

                    if (serviceResponse.success ==true){
                        withContext(Dispatchers.Main){
                            Toast.makeText(this@add_services, "Service added", Toast.LENGTH_SHORT).show()
                        }
//                        startActivity(Intent(this@UserRegister, LoginActivity::class.java))
                    }
                }catch (ex:Exception){
                    withContext(Dispatchers.Main){
                        Toast.makeText(this@add_services, ex.toString(), Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
        else{
            Toast.makeText(this, "Invalid Input", Toast.LENGTH_SHORT).show()
        }
    }
    private fun isValid():Boolean{
        when{
            addServiceName.text.isEmpty() -> {
                addServiceName.error="Service Name cannot be Empty"
                addServiceName.requestFocus()
                return false
            }
            addServicePrice.text.isEmpty() -> {
                addServicePrice.error="Service Price cannot be Empty"
                addServicePrice.requestFocus()
                return false
            }
            addServiceAbout.text.isEmpty() -> {
                addServiceAbout.error="Please tell about service"
                addServiceAbout.requestFocus()
                return false
            }
        }
        return true
    }
}