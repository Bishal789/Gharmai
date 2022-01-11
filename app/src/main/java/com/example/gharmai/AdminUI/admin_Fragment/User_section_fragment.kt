package com.example.gharmai.AdminUI.admin_Fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.example.gharmai.AdminUI.add_services
import com.example.gharmai.R


class User_section_fragment : Fragment() {


    private lateinit var ADD_Service:Button



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.user_section_fragment, container, false)

        ADD_Service = view.findViewById(R.id.ADD_SERVICES)

        ADD_Service.setOnClickListener {
            startActivity(Intent(activity,add_services::class.java))
        }









        return view
    }


}