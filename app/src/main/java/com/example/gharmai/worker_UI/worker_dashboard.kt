package com.example.gharmai.worker_UI

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.webkit.WebView
import android.widget.RelativeLayout
import android.widget.Switch
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.gharmai.Fragment.CartFragment
import com.example.gharmai.Fragment.HomeFragment
import com.example.gharmai.Fragment.ProfileFragment
import com.example.gharmai.R
import com.example.gharmai.worker_UI.worker_fragment.Map_Fragment
import com.example.gharmai.worker_UI.worker_fragment.Profile_fragment_worker
import com.google.android.material.bottomnavigation.BottomNavigationView

class worker_dashboard : AppCompatActivity() {


    private lateinit var tvStatus: TextView
    private lateinit var switch: Switch
    private lateinit var info: TextView

    private val HomeFragment = Map_Fragment()
    private val ProfileFragment = Profile_fragment_worker()

    private lateinit var container: RelativeLayout


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_worker_dashboard)


        tvStatus = findViewById(R.id.tvstatus)
        switch = findViewById(R.id.status)
        container = findViewById(R.id.worker_fragment_Container)
        info = findViewById(R.id.info)


        container.visibility = View.GONE


        replacefragment(HomeFragment)
        val bottomNav = findViewById<BottomNavigationView>(R.id.botNav)
        bottomNav.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.home_worker -> replacefragment(HomeFragment)
                R.id.profile_worker -> replacefragment(ProfileFragment)
            }
            true
        }



        //        val text = "Nothing Check"
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