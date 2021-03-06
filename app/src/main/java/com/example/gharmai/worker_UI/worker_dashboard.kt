package com.example.gharmai.worker_UI

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.Switch
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.gharmai.R
import com.example.gharmai.worker_UI.worker_fragment.user_request

class worker_dashboard : AppCompatActivity() {


    private lateinit var tvStatus: TextView
    private lateinit var switch: Switch
    private lateinit var info: TextView
    private lateinit var profile_img:ImageView

    private val HomeFragment = user_request()
//    private val ProfileFragment = Profile_fragment_worker()

    private lateinit var container: RelativeLayout


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_worker_dashboard)


        tvStatus = findViewById(R.id.tvstatus)
        switch = findViewById(R.id.status)
        container = findViewById(R.id.worker_fragment_Container)
        info = findViewById(R.id.info)
        profile_img = findViewById(R.id.profile_img)


        container.visibility = View.GONE


        replacefragment(HomeFragment)


        profile_img.setOnClickListener{
            startActivity(Intent(this@worker_dashboard, worker_profileSection::class.java))
        }


        switch.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked){
                // when switch button is checked
                tvStatus.text = "Online"

                info.visibility = View.GONE
                container.visibility = View.VISIBLE



            }else{
                // if switch button is unchecked

                tvStatus.text = "Offline"
                info.visibility = View.VISIBLE
                container.visibility = View.GONE



            }
        }
    }

    private fun replacefragment(fragment: Fragment) {
        if (fragment != null) {
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.worker_fragment_Container, fragment)
            transaction.commit()
        }

    }







}