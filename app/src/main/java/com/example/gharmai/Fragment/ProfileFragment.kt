package com.example.gharmai.Fragment

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.PopupMenu
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.bumptech.glide.Glide
import com.example.gharmai.R
import com.example.gharmai.UI.LoginActivity
import com.example.gharmai.UI.User_editProfile
import com.example.gharmai.api.ServiceBuilder
import com.example.gharmai.repository.UserRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class ProfileFragment : Fragment() {

    private lateinit var menuImg: ImageView
    private lateinit var profileImage: ImageView
    private lateinit var editMail: TextView
    private lateinit var editAddress: TextView
    private lateinit var editPhone: TextView
    private lateinit var editUsername: TextView


    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_profile, container, false)

        editMail = view.findViewById(R.id.etEmail)
        editAddress = view.findViewById(R.id.etAddress)
        editPhone = view.findViewById(R.id.etPhone)
        editUsername = view.findViewById(R.id.etUsername)
        profileImage = view.findViewById(R.id.userprofile)
        menuImg = view.findViewById(R.id.menuProfile)
//
        menuImg.setOnClickListener {
            loadProfilemenu()

        }
        CoroutineScope(Dispatchers.Main).launch {

//            Toast.makeText(this@User_Profile, "profile", Toast.LENGTH_SHORT).show()

            val repository = UserRepository()
            val response = repository.getCurrentUserAPI(ServiceBuilder.userId!!)


            Log.d("resfaf", "feafea")
            Log.d("resfaf", response.data.toString())

            Log.d("BeforeSuccessCheck", response.data.toString())
            if (response.success == true) {

                editAddress.setText(response.data!!.addressUser)
                editMail.setText(response.data.emailUser)
                editUsername.setText(response.data!!.username)
                editPhone.setText(response.data!!.phoneUser)

//                context?.let {
//                    Glide.with(it)
//                        .load(ServiceBuilder.BASE_URL + response.data.profile_pic)
//                        .into(profileImage)
//                }
            } else {
                withContext(Dispatchers.Main) {

//                    Toast.makeText(
//                        this@User_Profile,
//                        "Error", Toast.LENGTH_SHORT
//                    ).show()
                }
            }
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
                builder.setPositiveButton("Yes"){_,_->
                    CoroutineScope(Dispatchers.IO).launch {
                        val userrepo = UserRepository()
                        val response = userrepo.deleteuser(ServiceBuilder.userId!!)
                        try {
                            if (response.success == true) {
                                startActivity(Intent(context,LoginActivity::class.java))
                                requireActivity().finish()
                                withContext(Dispatchers.Main) {
                                    Toast.makeText(context, "User Deleted Successfully", Toast.LENGTH_SHORT)
                                        .show()
                                }
                            }
                        } catch (ex: Exception) {
                            withContext(Dispatchers.Main) {
                                Toast.makeText(context, ex.toString(), Toast.LENGTH_LONG).show()
                            }
                        }
                    }
                }
                builder.setNegativeButton("No"){_,_->
                    Toast.makeText(context, "Action Cancelled", Toast.LENGTH_SHORT).show()
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