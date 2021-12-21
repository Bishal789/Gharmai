package com.example.gharmai.UI

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.gharmai.R

class LoginChoice : AppCompatActivity() {

    private lateinit var userLogin: Button
    private lateinit var workerLogin:Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_choice)

        userLogin = findViewById(R.id.button)
        workerLogin= findViewById(R.id.button1)

        userLogin.setOnClickListener {
            val intent = Intent(this@LoginChoice, LoginActivity::class.java)
            startActivity(intent)
        }
    }
}