package com.example.gharmai.AdminUI.admin_Fragment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
//import com.example.gharmai.Adapter.UserAdapter
import com.example.gharmai.AdminUI.add_services
import com.example.gharmai.R
import com.example.gharmai.api.ServiceBuilder
import com.example.gharmai.entity.UserEntity
import com.example.gharmai.repository.UserRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class User_section_fragment : Fragment() {
    companion object {
        var lstUser: ArrayList<UserEntity> = ArrayList<UserEntity>()
    }

    private lateinit var ADD_Service: Button
    private lateinit var recyclerView: RecyclerView
    private lateinit var btnDelete: Button


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.user_section_fragment, container, false)

        ADD_Service = view.findViewById(R.id.ADD_SERVICES)
        recyclerView = view.findViewById(R.id.recyclerview12)
        btnDelete = view.findViewById(R.id.button_DELETE_ADMIN)

        ADD_Service.setOnClickListener {
            startActivity(Intent(activity, add_services::class.java))
        }

//        getUser()

        btnDelete.setOnClickListener {
//            delete()
        }
        return view
    }

//    private fun getUser() {
//        lstUser = ArrayList<UserEntity>()
//        CoroutineScope(Dispatchers.Main).launch {
//            try {
//                val repository = UserRepository()
//                val response = repository.getUserDetails(ServiceBuilder.userId!!)
//                if (response.success == true) {
//                    response.data?.forEach { item ->
//                        lstUser.add(item)
//                    }
//                    withContext(Dispatchers.Main) {
//                        val adapter = context?.let { UserAdapter(lstUser, it) }
//                        recyclerView.layoutManager =
//                            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
//                        recyclerView.adapter = adapter
//                    }
//                }
//            } catch (ex: Exception) {
//                withContext(Dispatchers.Main) {
//                    Toast.makeText(context, ex.toString(), Toast.LENGTH_SHORT).show()
//                }
//            }
//        }
//    }

//    private fun delete() {
//        lstUser = ArrayList<UserEntity>()
//        val builder = android.app.AlertDialog.Builder(context)
//        builder.setTitle("Delete Profile")
//        builder.setMessage("Are you sure you want to delete your profile??")
//        builder.setIcon(android.R.drawable.ic_dialog_alert)
//        builder.setPositiveButton("Yes") { _, _ ->
//            CoroutineScope(Dispatchers.Main).launch {
//                try {
//                    val repository = UserRepository()
//                    val response = repository.deleteuser(ServiceBuilder.userId!!)
//                    if (response.success == true) {
//                        response.data?.forEach { item ->
//                            lstUser.remove(item)
//                        }
//                        withContext(Dispatchers.Main) {
//                            val adapter = context?.let { UserAdapter(lstUser, it) }
//                            recyclerView.layoutManager =
//                                LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
//                            recyclerView.adapter = adapter
//                        }
//                    }
//                } catch (ex: Exception) {
//                    withContext(Dispatchers.Main) {
//                        Toast.makeText(context, ex.toString(), Toast.LENGTH_SHORT).show()
//                    }
//                }
//            }
//        }
//    }
}