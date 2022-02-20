package com.example.gharmai.Adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.gharmai.InsideUI.ServiceView
import com.example.gharmai.R
import com.example.gharmai.api.ServiceBuilder
import com.example.gharmai.entity.ServiceEntity
import com.example.gharmai.repository.ServiceRepository
import com.example.gharmai.repository.UserRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ServiceAdapter(
    private val context: Context,
    private val serviceList: MutableList<ServiceEntity>
): RecyclerView.Adapter<ServiceAdapter.ServiceViewHolder>() {

    class ServiceViewHolder(view: View): RecyclerView.ViewHolder(view){

        val serviceName: TextView = view.findViewById(R.id.tvServiceName)
        val serviceDetail: TextView = view.findViewById(R.id.tvServiceDetail)
        val servicePrice: TextView = view.findViewById(R.id.tvServicePrice)
        val btnServiceBook: Button = view.findViewById(R.id.btnBookService)
        val serviceImage: ImageView = view.findViewById(R.id.serviceImage)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ServiceViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.user_services_layout, parent, false)
        return ServiceViewHolder(view)
    }

    override fun onBindViewHolder(holder: ServiceViewHolder, position: Int) {


        val services = serviceList[position]
        var serviceID = services._id
        var serviceCategory = services.serviceCategory
        println(services)
        holder.serviceName.text = services.serviceName
        holder.serviceDetail.text = services.serviceDetails
        holder.servicePrice.text = services.servicePrice

//        Glide.with(context)
//            .load(ServiceBuilder.BASE_URL + services.serviceImage)
//            .into(holder.serviceImage)

        holder.btnServiceBook.setOnClickListener {

            val intent = Intent(context, ServiceView::class.java)
            intent.putExtra("service", services)
            context.startActivity(intent)
        }


//        holder.btnServiceBook.setOnClickListener {
//            val builder = android.app.AlertDialog.Builder(context)
//            builder.setTitle("Confirm to Book !!")
//            builder.setIcon(android.R.drawable.ic_dialog_alert)
//            builder.setPositiveButton("Yes"){_,_->
//                if (serviceID != null){
//                    serviceBook(serviceID)
////                    serviceBook(serviceID)
////                    serviceList.remove(services)
////                    notifyDataSetChanged()
//                }
////                    Toast.makeText(context, "Music Successfully Deleted", Toast.LENGTH_SHORT).show()
//            }
//            builder.setNegativeButton("No"){_,_->
//                Toast.makeText(context, "Action Cancelled", Toast.LENGTH_SHORT).show()
//            }
//            val alertDialog: android.app.AlertDialog = builder.create()
//            alertDialog.setCancelable(false)
//            alertDialog.show()
//        }
    }

    private fun serviceBook(serviceID: String) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val repository = ServiceRepository()
                val response = repository.bookService(
                    ServiceBuilder.token!!,
                    ServiceBuilder.userId!!,
                    serviceID
                )
                if (response.success==true) {
//                    sevices_adding.serviceList?.remove(ServiceEntity())
                    withContext(Dispatchers.Main) {
                        Toast.makeText(
                            context,
                            "Service booked", Toast.LENGTH_SHORT
                        ).show()

                    }

                } else {
                    withContext(Dispatchers.Main) {
                        Toast.makeText(
                            context,
                            "Booking failed", Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            } catch (ex: Exception) {
                withContext(Dispatchers.Main) {
                    Toast.makeText(
                        context,
                        ex.toString(), Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }

    }

    fun serviceDelete(serviceID: String) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val repository = ServiceRepository()
                val response = repository.deleteService(serviceID)
                if (response.success==true) {
//                    sevices_adding.serviceList?.remove(ServiceEntity())
                    withContext(Dispatchers.Main) {
                        Toast.makeText(
                            context,
                            "Service deleted", Toast.LENGTH_SHORT
                        ).show()

                    }

                } else {
                    withContext(Dispatchers.Main) {
                        Toast.makeText(
                            context,
                            "Delete failed", Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            } catch (ex: Exception) {
                withContext(Dispatchers.Main) {
                    Toast.makeText(
                        context,
                        ex.toString(), Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }

    }

    override fun getItemCount(): Int {
        return serviceList.size
    }
}