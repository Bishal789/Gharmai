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
import com.example.gharmai.InsideUI.salon_women
import com.example.gharmai.R
import com.example.gharmai.entity.BookEntity
import com.example.gharmai.entity.ServiceBook
import com.example.gharmai.repository.ServiceRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class CartFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView

    companion object {
        var bookedList: ArrayList<ServiceBook>? = ArrayList()
        var data: BookEntity? = null
//        private var userList: MutableList<UserEntity>? = ArrayList()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_cart, container, false)

        recyclerView = view.findViewById(R.id.bookingViewSection)
//        bookingView()
        serviceView()

        return view
    }

    private fun serviceView() {
        CoroutineScope(Dispatchers.IO).launch {
            try {

                val serviceRepository = ServiceRepository()
                val serviceResponse = serviceRepository.getAllServiceAPI()

                if(serviceResponse.success == true){
                    val serviceLists = serviceResponse.data
                    salon_women.serviceList = serviceLists
                    val serviceAdapter = serviceLists?.let { ServiceAdapter(requireContext(), it) }

                    withContext(Dispatchers.Main){
                        recyclerView.layoutManager = LinearLayoutManager(context,
                            LinearLayoutManager.VERTICAL, false)
                        recyclerView.adapter = serviceAdapter
                    }
                }
            }catch(ex: Exception){
                withContext(Dispatchers.Main){
                    Toast.makeText(context, ex.toString(), Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun bookingView() {

        bookedList = ArrayList<ServiceBook>()

        CoroutineScope(Dispatchers.IO).launch {
            try {

                val serviceRepository = ServiceRepository()
                val serviceResponse = serviceRepository.getBooking()

                if (serviceResponse.success == true) {
                    val bookedLists = serviceResponse.bookedItem
                    val booked = bookedLists?.service

                    if (booked != null) {
                        for (i in booked) {
                            bookedList!!.add(
                                ServiceBook(
                                    serviceID = i.serviceID,
                                    serviceName = i.serviceName,
                                    serviceDetails = i.servicePrice,
                                    servicePrice = i.servicePrice
                                )
                            )
                        }
                    }
                    withContext(Dispatchers.Main) {
//                        val serviceAdapter = context?.let { BookedViewAdapter(it, userLists) }
                        val serviceAdapter = BookedViewAdapter(requireContext(), bookedList)
                        recyclerView.layoutManager =
                            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
                        recyclerView.adapter = serviceAdapter
                    }
                } else {
                    Toast.makeText(context, "No data found", Toast.LENGTH_SHORT).show()
                }
            } catch (ex: Exception) {
                withContext(Dispatchers.Main) {
                    Toast.makeText(context, ex.toString(), Toast.LENGTH_SHORT).show()
                }
            }
        }

    }

}