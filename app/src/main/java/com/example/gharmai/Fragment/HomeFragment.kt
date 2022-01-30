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
import com.example.gharmai.InsideUI.*
import com.example.gharmai.R

class HomeFragment : Fragment() {

    private lateinit var salonWomen: CardView
    private lateinit var salonMen: CardView
    private lateinit var plumber: CardView
    private lateinit var electricians: CardView
    private lateinit var cleaningAndDisinfection: CardView
    private lateinit var menTherapy: CardView
    private lateinit var womenTherapy: CardView
    private lateinit var carpenter: CardView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_home, container, false)

        salonWomen = view.findViewById(R.id.salonWomen)
        salonMen = view.findViewById(R.id.salonMen)
        plumber = view.findViewById(R.id.plumber)
        electricians = view.findViewById(R.id.electricians)
        cleaningAndDisinfection = view.findViewById(R.id.cleaningAndDisinfection)
        carpenter = view.findViewById(R.id.carpenter)
        menTherapy = view.findViewById(R.id.menTherapy)
        womenTherapy = view.findViewById(R.id.womenTherapy)

        salonWomen.setOnClickListener {
            startActivity(Intent(activity, salon_women::class.java))
        }

        salonMen.setOnClickListener {
            startActivity(Intent(activity, salon_men::class.java))
        }

        plumber.setOnClickListener {
            startActivity(Intent(activity, com.example.gharmai.InsideUI.plumber::class.java))
        }

        electricians.setOnClickListener {
            startActivity(Intent(activity, com.example.gharmai.InsideUI.electricians::class.java))
        }

        cleaningAndDisinfection.setOnClickListener {
            startActivity(Intent(activity, cleaning_disinfection::class.java))
        }

        carpenter.setOnClickListener {
            startActivity(Intent(activity, com.example.gharmai.InsideUI.carpenter::class.java))
        }

        menTherapy.setOnClickListener {
            startActivity(Intent(activity, men_therapy::class.java))
        }

        womenTherapy.setOnClickListener {
            startActivity(Intent(activity, women_therapy::class.java))
        }


        val imageSlider = view.findViewById<ImageSlider>(R.id.imageSlider)
        val imageList = ArrayList<SlideModel>()

        imageList.add(
            SlideModel(
                "https://www.mirrorsbeautylounge.com/mirror-admin/public/images/offers/exclusive-offer-mb-min.jpg",
                "ada"
            )
        )
        imageList.add(
            SlideModel(
                "https://www.mirrorsbeautylounge.com/mirror-admin/public/images/offers/exclusive-offer-mb-min.jpg",
                "q"
            )
        )
        imageList.add(
            SlideModel(
                "https://www.mirrorsbeautylounge.com/mirror-admin/public/images/offers/exclusive-offer-mb-min.jpg",
                "qq"
            )
        )
        imageList.add(
            SlideModel(
                "https://www.mirrorsbeautylounge.com/mirror-admin/public/images/offers/exclusive-offer-mb-min.jpg",
                "qqq"
            )
        )
        imageList.add(
            SlideModel(
                "https://www.mirrorsbeautylounge.com/mirror-admin/public/images/offers/exclusive-offer-mb-min.jpg",
                "qqqq"
            )
        )
        imageList.add(
            SlideModel(
                "https://www.mirrorsbeautylounge.com/mirror-admin/public/images/offers/exclusive-offer-mb-min.jpg",
                "qqqqq"
            )
        )
        imageList.add(
            SlideModel(
                "https://www.mirrorsbeautylounge.com/mirror-admin/public/images/offers/exclusive-offer-mb-min.jpg",
                "qqqqqq"
            )
        )


        imageSlider.setImageList(imageList, ScaleTypes.FIT)





        return view


    }
}