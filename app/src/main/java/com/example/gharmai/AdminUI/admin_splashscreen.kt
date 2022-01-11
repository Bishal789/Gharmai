package com.example.gharmai.AdminUI

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.example.gharmai.R
import com.example.gharmai.UI.LoginChoice

class admin_splashscreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_splashscreen)

        Handler().postDelayed({
            startActivity(Intent(this@admin_splashscreen, Admin_dasboard::class.java))
        },4000)
    }
}