package com.example.gharmai.InsideUI

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.example.gharmai.R
import com.example.gharmai.api.ServiceBuilder
import com.example.gharmai.entity.ServiceEntity
import com.example.gharmai.repository.ServiceRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ServiceView : AppCompatActivity() {

    private lateinit var serviceName: TextView
    private lateinit var servicePrice: TextView
    private lateinit var serviceDescription: TextView
    private lateinit var bookingButton: Button
    private var serviceID : String? = null
    private var serviceNames : String? = null
    private var servicePrices : String? = null
    private var serviceDescriptions : String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_service_view)

        serviceName = findViewById(R.id.serviceName11)
        servicePrice = findViewById(R.id.servicePrice)
        serviceDescription = findViewById(R.id.serviceAbout)
        bookingButton = findViewById(R.id.subButton)
        getServiceData()

        bookingButton.setOnClickListener {
            bookService()
        }
    }

    private fun bookService() {
        CoroutineScope(Dispatchers.IO).launch {
            try{

                val subRepo = ServiceRepository()
                val response = subRepo.bookService(
                    ServiceBuilder.token!!,
                    ServiceBuilder.userId!!,
                    serviceID.toString())

                if(response.success==true){
                    withContext(Dispatchers.Main){
                        Toast.makeText(this@ServiceView,
                            "Service Booked",
                            Toast.LENGTH_SHORT).show()
                        finish()
                    }
                }

                if(response.success==false){
                    withContext(Dispatchers.Main) {
                        Toast.makeText(
                            this@ServiceView,
                            "Already Booked",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }catch(ex : Exception){
                withContext(Dispatchers.Main){
                    Log.d("error", ex.printStackTrace().toString())
                    Toast.makeText(this@ServiceView, ex.toString(), Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun getServiceData() {
        val serviceData = intent.getParcelableExtra<ServiceEntity>("service")

        serviceID = serviceData?._id
        serviceNames = serviceData?.serviceName
        servicePrices = serviceData?.servicePrice
        serviceDescriptions = serviceData?.serviceDetails

        serviceName.text = serviceNames
        servicePrice.text = "Rs: $servicePrices"
        serviceDescription.text = serviceDescriptions
    }
}