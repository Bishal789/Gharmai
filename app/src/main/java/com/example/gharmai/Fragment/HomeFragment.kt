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
                "Salon for Women"
            )
        )
        imageList.add(
            SlideModel(
                "https://media.istockphoto.com/photos/hairdressers-cutting-hair-of-clients-in-salon-picture-id1030251504?k=20&m=1030251504&s=612x612&w=0&h=vZHCCKNNfg5lxNYSVNYGcfjCtEuL8h8rMAC4NwAS_YU=",
                "Salon for men"
            )
        )
        imageList.add(
            SlideModel(
                "https://res.cloudinary.com/jerrick/image/upload/v1615462800/604a0190351cde001d89dab6.jpg",
                "Plumber"
            )
        )
        imageList.add(
            SlideModel(
                "https://www.forbes.com/advisor/wp-content/uploads/2021/04/featured-image-hire-an-electrician.jpeg.jpg",
                "Electrician"
            )
        )
        imageList.add(
            SlideModel(
                "http://blog.novaerus.com/wp-content/uploads/otwpct/tmb/Surface_Cleaning_Hospital_1537188543_1180X580_c_c_0_0.jpg",
                "Cleaning and Disinfection"
            )
        )
        imageList.add(
            SlideModel(
                "https://www.thebalancesmb.com/thmb/3zcgjdCDA5cVNoCLBrMrgJeqtdw=/3437x2578/smart/filters:no_upscale()/young-stylish-cabinet-maker-with--glasses-and-hairstyle--strong--handsome-craftsman-holding-saw-and-wood-blank-at-workplace-944613244-5af9afc2a18d9e003c17040c.jpg",
                "Carpenter"
            )
        )
        imageList.add(
            SlideModel(
                "https://s3.envato.com/files/304306291/373_E39A5334.jpg",
                "Men Therapy"
            )
        )
        imageList.add(
            SlideModel(
                "https://www.traumaandbeyondcenter.com/wp-content/uploads/2020/06/group-therapy-1.jpg",
                "Women Therapy"
            )
        )


        imageSlider.setImageList(imageList, ScaleTypes.FIT)





        return view


    }
}