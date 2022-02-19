package com.example.gharmai.UI

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.gharmai.R
import com.example.gharmai.worker_UI.worker_login

class LoginChoice : AppCompatActivity() {

    private lateinit var userLogin: Button
    private lateinit var workerLogin:Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_choice)

        userLogin = findViewById(R.id.button)
        workerLogin= findViewById(R.id.button1)

        userLogin.setOnClickListener {
            startActivity(Intent(this@LoginChoice, LoginActivity::class.java))
        }
        workerLogin.setOnClickListener {
            startActivity(Intent(this@LoginChoice, worker_login::class.java))
        }
    }
}