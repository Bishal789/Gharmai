package com.example.gharmai.UI

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.example.gharmai.R

class worker_login : AppCompatActivity() {

    private lateinit var register: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_worker_login)


        register = findViewById(R.id.dnthave_worker)

        register.setOnClickListener {
            startActivity(Intent(this@worker_login,worker_register::class.java))
        }
    }
}