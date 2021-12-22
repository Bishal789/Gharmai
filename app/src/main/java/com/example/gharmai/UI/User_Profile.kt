package com.example.gharmai.UI

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.PopupMenu
import android.widget.Toast
import com.example.gharmai.R

class User_Profile : AppCompatActivity() {


    private lateinit var userImg: ImageView



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_profile)


        userImg = findViewById(R.id.menuProfile)

        userImg.setOnClickListener {
            loadProfilemenu()

        }




    }
    private fun loadProfilemenu() {
        val popMenu = PopupMenu(this, userImg)
        popMenu.menuInflater.inflate(R.menu.profile_menu, popMenu.menu)
        popMenu.setOnMenuItemClickListener { item ->
            if (item.itemId == R.id.edit_profile) {

                val intent = Intent(this@User_Profile,User_editProfile::class.java)
                startActivity(intent)

//                Toast.makeText(this, "Profile Edited", Toast.LENGTH_SHORT)
//                    .show()
            } else if (item.itemId == R.id.delete_profile) {

                val builder = android.app.AlertDialog.Builder(this)
                builder.setTitle("Are You Sure want to Delete your Profile?")
                builder.setIcon(android.R.drawable.ic_dialog_alert)
                builder.setPositiveButton("Yes"){_,_->

                    Toast.makeText(this, "Profile Successfully Deleted", Toast.LENGTH_SHORT).show()
                }
                builder.setNegativeButton("No"){_,_->
                    Toast.makeText(this, "Action Cancelled", Toast.LENGTH_SHORT).show()
                }
                val alertDialog: android.app.AlertDialog = builder.create()
                alertDialog.setCancelable(false)
                alertDialog.show()
            }
            true
        }
        popMenu.show()
    }
}