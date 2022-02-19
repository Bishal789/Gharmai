package com.example.gharmai.InsideUI

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.gharmai.Adapter.ServiceAdapter
import com.example.gharmai.R
import com.example.gharmai.entity.ServiceEntity
import com.example.gharmai.repository.ServiceRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class salon_women : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView

    companion object{
        var serviceList: MutableList<ServiceEntity>? = ArrayList()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_salon_women)

        recyclerView = findViewById(R.id.salonForWomenRecycle)
        viewService()

    }

    private fun viewService() {
//        serviceList = ArrayList()

        CoroutineScope(Dispatchers.IO).launch {
            try {

                val serviceRepository = ServiceRepository()
                val serviceResponse = serviceRepository.getAllServiceAPI()

                if(serviceResponse.success == true){
                    val serviceLists = serviceResponse.data
                    serviceList = serviceLists
                    val serviceAdapter = serviceLists?.let { ServiceAdapter(this@salon_women, it) }

                    withContext(Dispatchers.Main){
                        recyclerView.layoutManager = LinearLayoutManager(this@salon_women,
                            LinearLayoutManager.VERTICAL, false)
                        recyclerView.adapter = serviceAdapter
                    }
                }
            }catch(ex: Exception){
                withContext(Dispatchers.Main){
                    Toast.makeText(this@salon_women, ex.toString(), Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}