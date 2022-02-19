package com.example.gharmai.Fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.gharmai.Adapter.BookedViewAdapter
import com.example.gharmai.Adapter.ServiceAdapter
import com.example.gharmai.R
import com.example.gharmai.entity.BookEntity
import com.example.gharmai.entity.ServiceEntity
import com.example.gharmai.repository.ServiceRepository
import com.example.gharmai.repository.UserRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class CartFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView

    companion object{
        var bookedList: ArrayList<ServiceEntity>? = ArrayList()
//        private var userList: MutableList<UserEntity>? = ArrayList()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_cart, container, false)

        recyclerView = view.findViewById(R.id.bookingViewSection)
        bookingView()

        return view


    }

    private fun bookingView() {

        bookedList = ArrayList()

        CoroutineScope(Dispatchers.IO).launch {
            try {

                val serviceRepository = ServiceRepository()
                val serviceResponse = serviceRepository.getBooking()

                if(serviceResponse.success== true){
                    val userLists = serviceResponse.data

                    if (userLists != null) {
                        userLists.forEach { item -> userLists.add(item)
                        }
                        withContext(Dispatchers.Main){
                            val serviceAdapter = context?.let { BookedViewAdapter(it, userLists) }
                            recyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
                            recyclerView.adapter = serviceAdapter
                        }
                    }else{
                        Toast.makeText(context, "No data found", Toast.LENGTH_SHORT).show()
                    }
                }

            }catch (ex: Exception){
                withContext(Dispatchers.Main){
                    Toast.makeText(context, ex.toString(), Toast.LENGTH_SHORT).show()
                }
            }
        }

    }

}