package com.example.gharmai.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.gharmai.R
import com.example.gharmai.api.ServiceBuilder
import com.example.gharmai.entity.UserEntity
import java.util.ArrayList

class UserAdapter (
    val lstUser: ArrayList<UserEntity>,
    val context: Context
): RecyclerView.Adapter<UserAdapter.UserViewHolder>(){

    //view holder
    class UserViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imgUserImage: ImageView = view.findViewById(R.id.USER_IMAGE)
        val tvUserName: TextView = view.findViewById(R.id.USERNAME_ADMIN)
        val tvUserEmail: TextView = view.findViewById(R.id.USER_EMAIL_ADMIN)
        val tvUserAddress: TextView = view.findViewById(R.id.USER_ADDRESS_ADMIN)
        val tvUserContact: TextView = view.findViewById(R.id.USER_CONTACT_ADMIN)
        val tvUserGender: TextView = view.findViewById(R.id.USER_GENDER_ADMIN)
    }

    // Actual Kursi Banako
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.admin_user_crud_layout, parent, false)
        return UserViewHolder(view)
    }

    //Bhitra ko content lai change gareko
    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        // euta employee lai extract gareko
        val user = lstUser[position]

        holder.tvUserName.text = user.username
        holder.tvUserEmail.text = user.emailUser
        holder.tvUserAddress.text = user.addressUser
        holder.tvUserContact.text = user.phoneUser
        holder.tvUserGender.text = user.genderUser

        Glide.with(context)
            .load(ServiceBuilder.BASE_URL+user.profile_pic)
            .circleCrop()
            .into(holder.imgUserImage)
    }

    override fun getItemCount(): Int {
        return lstUser.size
    }
}