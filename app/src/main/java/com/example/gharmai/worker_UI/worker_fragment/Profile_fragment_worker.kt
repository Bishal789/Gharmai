package com.example.gharmai.worker_UI.worker_fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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


class Profile_fragment_worker : Fragment() {

    private lateinit var menuImg: ImageView


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_profile_worker, container, false)

        menuImg = view.findViewById(R.id.menuProfile_worker)


        menuImg.setOnClickListener {
            loadProfilemenu()

        }


        return view

    }


    private fun loadProfilemenu() {
        val popMenu = PopupMenu(context, menuImg)
        popMenu.menuInflater.inflate(R.menu.profile_menu, popMenu.menu)
        popMenu.setOnMenuItemClickListener { item ->
            if (item.itemId == R.id.edit_profile) {

                val intent = Intent(activity, User_editProfile::class.java)
                startActivity(intent)

//                Toast.makeText(this, "Profile Edited", Toast.LENGTH_SHORT)
//                    .show()
            } else if (item.itemId == R.id.delete_profile) {

                val builder = android.app.AlertDialog.Builder(context)
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
                                                context,
                                                "User Deleted Successfully",
                                                Toast.LENGTH_SHORT
                                            )
                                                .show()

                                        }
                                        startActivity(Intent(context, LoginActivity::class.java))
                                        requireActivity().finish()
                                    }
                                } catch (ex: Exception) {
                                    withContext(Dispatchers.Main) {
                                        Toast.makeText(context, ex.toString(), Toast.LENGTH_LONG)
                                            .show()
                                    }
                                }
                            }

                            Toast.makeText(
                                context,
                                "Profile Successfully Deleted",
                                Toast.LENGTH_SHORT
                            ).show()

                        }
                        catch (ex: Exception) {
                            withContext(Dispatchers.Main) {
                                Toast.makeText(context, ex.toString(), Toast.LENGTH_LONG)
                                    .show()
                            }
                        }

                    }
                }
                builder.setNegativeButton("No") { _, _ ->
                    android.widget.Toast.makeText(context, "Action Cancelled", android.widget.Toast.LENGTH_SHORT).show()
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