package com.example.gharmai.worker_UI.worker_fragment

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.gharmai.Notification.Notification
import com.example.gharmai.R


class user_request : Fragment() {

    private lateinit var btnRequest: Button





    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view =  inflater.inflate(R.layout.fragment_user_request, container, false)

        btnRequest=view.findViewById(R.id.btnAcptService)


        return view
    }
//    fun musicPlay() {
//        val notificationManager = NotificationManagerCompat.from(context)
//
//        val notificationChannels = Notification(context)
//        notificationChannels.createNotificationChannels()
//
//        val notification = NotificationCompat.Builder(context, notificationChannels.CHANNEL_1)
//            .setSmallIcon(R.drawable.ic_pause)
////            .setVibrate(longArrayOf(0, 1000, 50, 2000))
//            .setContentTitle("Music Playing..")
////            .setContentText("Is this you?")
//            .setColor(Color.BLUE)
//            .build()
//
//        notificationManager.notify(1, notification)
//    }


}