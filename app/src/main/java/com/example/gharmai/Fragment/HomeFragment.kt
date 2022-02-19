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

    private lateinit var salonWomenFrag: CardView
    private lateinit var salonMenFrag: CardView
    private lateinit var plumberFrag: CardView
    private lateinit var electriciansFrag: CardView
    private lateinit var cleaningAndDisinfectionFrag: CardView
    private lateinit var menTherapyFrag: CardView
    private lateinit var womenTherapyFrag: CardView
    private lateinit var carpenterFrag: CardView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_home, container, false)

        salonWomenFrag = view.findViewById(R.id.salonWomen)
        salonMenFrag = view.findViewById(R.id.salonMen)
        plumberFrag = view.findViewById(R.id.plumber)
        electriciansFrag = view.findViewById(R.id.electricians)
        cleaningAndDisinfectionFrag = view.findViewById(R.id.cleaningAndDisinfection)
        carpenterFrag = view.findViewById(R.id.carpenter)
        menTherapyFrag = view.findViewById(R.id.menTherapy)
        womenTherapyFrag = view.findViewById(R.id.womenTherapy)

        salonWomenFrag.setOnClickListener {
            startActivity(Intent(activity, salon_women::class.java))
        }

        salonMenFrag.setOnClickListener {
            startActivity(Intent(activity, salon_men::class.java))
        }

        plumberFrag.setOnClickListener {
            startActivity(Intent(activity, plumber::class.java))
        }

        electriciansFrag.setOnClickListener {
            startActivity(Intent(activity, electricians::class.java))
        }

        cleaningAndDisinfectionFrag.setOnClickListener {
            startActivity(Intent(activity, cleaning_disinfection::class.java))
        }

        carpenterFrag.setOnClickListener {
            startActivity(Intent(activity, carpenter::class.java))
        }

        menTherapyFrag.setOnClickListener {
            startActivity(Intent(activity, men_therapy::class.java))
        }

        womenTherapyFrag.setOnClickListener {
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