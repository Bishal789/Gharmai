package com.example.gharmai.Adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.gharmai.AdminUI.admin_Fragment.User_section_fragment
import com.example.gharmai.R
import com.example.gharmai.entity.ServiceEntity
import com.example.gharmai.repository.ServiceRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class admin_ServiceAdapter(
    private val context: Context,
    private val serviceList: MutableList<ServiceEntity>
) : RecyclerView.Adapter<admin_ServiceAdapter.ServiceHolder>() {


    class ServiceHolder(view: View) : RecyclerView.ViewHolder(view) {

        val tvServiceName: TextView = view.findViewById(R.id.tvServiceName)
        val tvServiceDetail: TextView = view.findViewById(R.id.tvServiceDetail)
        val tvServicePrice: TextView = view.findViewById(R.id.tvServicePrice)
        val ivServiceImage: ImageView = view.findViewById(R.id.serviceImage)
        val btnDelete: TextView = view.findViewById(R.id.btnDelete)
        val serviceLayout: RelativeLayout = view.findViewById(R.id.ServiceLayout)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ServiceHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.services_adding_admin, parent, false)
        return ServiceHolder(view)

    }

    override fun onBindViewHolder(holder: ServiceHolder, position: Int) {

        val service = serviceList[position]
        Log.d("fuc",service.serviceName.toString())
        holder.tvServiceName.text = service.serviceName
        holder.tvServiceDetail.text = service.serviceDetails
        holder.tvServicePrice.text = service.servicePrice

//        Glide.with(context)
//            .load(ServiceBuilder.BASE_URL +music.musicProfile)
//            .into(holder.ivImage)




        var id = service.categoryID
        holder.serviceLayout.setOnClickListener {
            val appCompatActivity = it.context as AppCompatActivity
        }


//        holder.btnDelete.setOnClickListener {
//
//            val builder = android.app.AlertDialog.Builder(context)
//            builder.setTitle("Are You Sure want to Delete?")
//            builder.setIcon(android.R.drawable.ic_dialog_alert)
//            builder.setPositiveButton("Yes"){_,_->
//                if (id != null){
//                    deleteApplied(id)
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


//    fun deleteApplied(id: String) {
//
//        CoroutineScope(Dispatchers.IO).launch {
//            try {
//
//                val repository = ServiceRepository()
//                val response = repository.deleteservice(id) //api xaina
//                if (response.success==true) {
//                    Toast.makeText(
//                        context,
//                        "Cannot delete.", Toast.LENGTH_SHORT
//                    ).show()
//
//                } else {
//                    withContext(Dispatchers.Main) {
//
//                        User_section_fragment.lstService.remove(ServiceEntity())
//                        Toast.makeText(
//                            context,
//                            "Deleted successfully", Toast.LENGTH_SHORT
//                        ).show()
//
//
//                    }
//                }
//
//            } catch (ex: Exception) {
//                withContext(Dispatchers.Main) {
//                    Toast.makeText(
//                        context,
//                        "Error", Toast.LENGTH_SHORT
//                    ).show()
//
//                }
//            }
//        }
//    }

    override fun getItemCount(): Int {
        return serviceList.size

    }
}