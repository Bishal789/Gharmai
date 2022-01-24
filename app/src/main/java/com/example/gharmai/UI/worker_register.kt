package com.example.gharmai.UI

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import com.example.gharmai.R

class worker_register : AppCompatActivity() {

    private lateinit var GenderSpinner: Spinner


    val genderType = arrayOf("Male","Female","Other")
    var gender : String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_worker_register)


        GenderSpinner = findViewById(R.id.genderSpinner_worker)

        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, genderType)
        GenderSpinner.adapter = adapter
        GenderSpinner.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    gender= parent?.getItemAtPosition(position).toString()
                }
                override fun onNothingSelected(parent: AdapterView<*>?) {
//                    TODO("Not yet implemented")
                }
            }

    }
}