package com.example.gharmai.InsideUI

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import com.example.gharmai.Fragment.HomeFragment
import com.example.gharmai.R
import com.example.gharmai.UI.Dashboard

class salon_women : AppCompatActivity() {

    private lateinit var backButtonn: ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_salon_women)


        backButtonn = findViewById(R.id.backButton)

        backButtonn.setOnClickListener {
            val intent = Intent(this, Dashboard::class.java)
            startActivity(intent)
        }
    }
}