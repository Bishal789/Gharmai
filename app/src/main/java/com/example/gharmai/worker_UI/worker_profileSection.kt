package com.example.gharmai.worker_UI

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.PopupMenu
import android.widget.Toast
import com.example.gharmai.R
import com.example.gharmai.UI.LoginActivity
import com.example.gharmai.UI.User_editProfile
import com.example.gharmai.api.ServiceBuilder
import com.example.gharmai.repository.UserRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class worker_profileSection : AppCompatActivity() {

    private lateinit var menuImg: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_worker_profile_section)


        menuImg = findViewById(R.id.menuProfile_worker)

        menuImg.setOnClickListener {
            loadProfilemenu()

        }





    }
    private fun loadProfilemenu() {
        val popMenu = PopupMenu(this, menuImg)
        popMenu.menuInflater.inflate(R.menu.profile_menu, popMenu.menu)
        popMenu.setOnMenuItemClickListener { item ->
            if (item.itemId == R.id.edit_profile) {

                val intent = Intent(this, User_editProfile::class.java)
                startActivity(intent)

//                Toast.makeText(this, "Profile Edited", Toast.LENGTH_SHORT)
//                    .show()
            } else if (item.itemId == R.id.delete_profile) {

                val builder = android.app.AlertDialog.Builder(this)
                builder.setTitle("Delete Profile")
                builder.setMessage("Are you sure you want to delete your profile??")
                builder.setIcon(android.R.drawable.ic_dialog_alert)
                builder.setPositiveButton("Yes") { _, _ ->
                    CoroutineScope(Dispatchers.IO).launch {

                        val userrepo = UserRepository()
                        val response = userrepo.deleteuser(ServiceBuilder.userId!!)
                        try {
                            if (response.success == true) {

                                val userRepo = UserRepository()
                                val response = userRepo.deleteuser(ServiceBuilder.userId!!)
                                try {

                                    if (response.success == true) {



                                        withContext(Dispatchers.Main) {
                                            Toast.makeText(
                                                this@worker_profileSection,
                                                "User Deleted Successfully",
                                                Toast.LENGTH_SHORT
                                            )
                                                .show()

                                        }
                                        startActivity(Intent(this@worker_profileSection, LoginActivity::class.java))
                                        finish()
                                    }
                                } catch (ex: Exception) {
                                    withContext(Dispatchers.Main) {
                                        Toast.makeText(this@worker_profileSection, ex.toString(), Toast.LENGTH_LONG)
                                            .show()
                                    }
                                }
                            }

                            Toast.makeText(
                                this@worker_profileSection,
                                "Profile Successfully Deleted",
                                Toast.LENGTH_SHORT
                            ).show()

                        }
                        catch (ex: Exception) {
                            withContext(Dispatchers.Main) {
                                Toast.makeText(this@worker_profileSection, ex.toString(), Toast.LENGTH_LONG)
                                    .show()
                            }
                        }

                    }
                }
                builder.setNegativeButton("No") { _, _ ->
                    android.widget.Toast.makeText(this@worker_profileSection, "Action Cancelled", android.widget.Toast.LENGTH_SHORT).show()
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