package com.example.gharmai.Fragment

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import com.denzcoskun.imageslider.ImageSlider
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.models.SlideModel
import com.example.gharmai.InsideUI.salon_women
import com.example.gharmai.R



class HomeFragment : Fragment() {

    private lateinit var salonWomen:CardView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_home, container, false)

        salonWomen = view.findViewById(R.id.salonWomen)

        salonWomen.setOnClickListener {
            startActivity(Intent(activity,salon_women::class.java))
        }





















        val imageSlider = view.findViewById<ImageSlider>(R.id.imageSlider)
        val imageList = ArrayList<SlideModel>()

        imageList.add(SlideModel("https://www.mirrorsbeautylounge.com/mirror-admin/public/images/offers/exclusive-offer-mb-min.jpg","ada"))
        imageList.add(SlideModel("https://www.mirrorsbeautylounge.com/mirror-admin/public/images/offers/exclusive-offer-mb-min.jpg","q"))
        imageList.add(SlideModel("https://www.mirrorsbeautylounge.com/mirror-admin/public/images/offers/exclusive-offer-mb-min.jpg","qq"))
        imageList.add(SlideModel("https://www.mirrorsbeautylounge.com/mirror-admin/public/images/offers/exclusive-offer-mb-min.jpg","qqq"))
        imageList.add(SlideModel("https://www.mirrorsbeautylounge.com/mirror-admin/public/images/offers/exclusive-offer-mb-min.jpg","qqqq"))
        imageList.add(SlideModel("https://www.mirrorsbeautylounge.com/mirror-admin/public/images/offers/exclusive-offer-mb-min.jpg","qqqqq"))
        imageList.add(SlideModel("https://www.mirrorsbeautylounge.com/mirror-admin/public/images/offers/exclusive-offer-mb-min.jpg","qqqqqq"))


        imageSlider.setImageList(imageList,ScaleTypes.FIT)





        return view


    }
}