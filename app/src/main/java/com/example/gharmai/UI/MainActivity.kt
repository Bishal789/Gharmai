package com.example.gharmai.UI

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.gharmai.R


class MainActivity : AppCompatActivity() {

    private lateinit var toProfile: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        toProfile = findViewById(R.id.toProfile)

        toProfile.setOnClickListener {
            startActivity(Intent(this@MainActivity, User_Profile::class.java))
        }
    }
}