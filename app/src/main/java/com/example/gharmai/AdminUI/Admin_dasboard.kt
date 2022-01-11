package com.example.gharmai.AdminUI

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.gharmai.AdminUI.admin_Fragment.User_section_fragment
import com.example.gharmai.Fragment.CartFragment
import com.example.gharmai.Fragment.HomeFragment
import com.example.gharmai.Fragment.ProfileFragment
import com.example.gharmai.R
import com.google.android.material.bottomnavigation.BottomNavigationView

class Admin_dasboard : AppCompatActivity() {

    private val User_Section_Fragment = User_section_fragment()
//    private var Worker_Section_Fragment = CartFragment()
//    private val Logout_Fragment = ProfileFragment()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.admin_dasboard)
        replacefragment(User_Section_Fragment)

        val bottomNav = findViewById<BottomNavigationView>(R.id.botNav_admin)
        bottomNav.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.user_section -> replacefragment(User_Section_Fragment)
//                R.id.cart -> replacefragment(CartFragment)
//                R.id.profile -> replacefragment(ProfileFragment)
            }
            true
        }


    }
    private fun replacefragment(fragment: Fragment) {
        if (fragment != null) {
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.admin_frag_Container, fragment)
            transaction.commit()
        }

    }
}