package com.example.gharmai.UI

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.*
import com.bumptech.glide.Glide
import com.example.gharmai.R
import com.example.gharmai.api.ServiceBuilder
import com.example.gharmai.repository.UserRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class User_Profile : AppCompatActivity() {


    private lateinit var userImg: ImageView
    private lateinit var editMail: TextView
    private lateinit var editAddress: TextView
    private lateinit var editPhone: TextView
    private lateinit var editUsername: TextView



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_profile)


        editMail = findViewById(R.id.etEmail)
        editAddress = findViewById(R.id.etAddress)
        editPhone = findViewById(R.id.etPhone)
        editUsername = findViewById(R.id.etUsername)

        userImg = findViewById(R.id.menuProfile)

        userImg.setOnClickListener {
            loadProfilemenu()

        }
        CoroutineScope(Dispatchers.Main).launch {

            Toast.makeText(this@User_Profile, "profile",Toast.LENGTH_SHORT).show()

            val repository = UserRepository()
            val response = repository.getCurrentUserAPI(ServiceBuilder.userId!!)


            Log.d("resfaf", "feafea")
            Log.d("resfaf", response.data.toString())

                Log.d("BeforeSuccessCheck", response.data.toString())
            if (response.success == true) {

                editAddress.text = response.data!!.addressUser
                editMail.text = response.data!!.emailUser
                editUsername.text = response.data!!.username
                editPhone.text = response.data!!.phoneUser
            } else {
                withContext(Dispatchers.Main) {

                    Toast.makeText(
                        this@User_Profile,
                        "Error", Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }

//        btnLogout.setOnClickListener {
//            Toast.makeText(activity, "You have been Logout Sucessfully", Toast.LENGTH_SHORT).show()
//            startActivity(
//                Intent(
//                    activity,
//                    Login::class.java
//                )
//            )
//            requireActivity().finish()
//        }
//
//        btnDelete.setOnClickListener {
//
//            deleteUser()
//
//
//
//        }

//        return view




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