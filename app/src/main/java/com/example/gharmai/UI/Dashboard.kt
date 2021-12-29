package com.example.gharmai.UI

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.gharmai.Fragment.CartFragment
import com.example.gharmai.Fragment.HomeFragment
import com.example.gharmai.Fragment.ProfileFragment
import com.example.gharmai.R
import com.google.android.material.bottomnavigation.BottomNavigationView


class Dashboard : AppCompatActivity() {

    private val HomeFragment = HomeFragment()
    private var CartFragment = CartFragment()
    private val ProfileFragment = ProfileFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)
        replacefragment(HomeFragment)

        val bottomNav = findViewById<BottomNavigationView>(R.id.botNav)
        bottomNav.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.home -> replacefragment(HomeFragment)
                R.id.cart -> replacefragment(CartFragment)
                R.id.profile -> replacefragment(ProfileFragment)
            }
            true
        }
    }
    private fun replacefragment(fragment: Fragment) {
        if (fragment != null) {
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.relativeContainer, fragment)
            transaction.commit()
        }

    }
}