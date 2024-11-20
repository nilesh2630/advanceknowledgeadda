package com.example.navdrawerscratch

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.cardview.widget.CardView
import com.denzcoskun.imageslider.ImageSlider
import com.denzcoskun.imageslider.models.SlideModel


class Homefragment : Fragment() {




    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view= inflater.inflate(R.layout.fragment_homefragment, container, false)
        val imageList = ArrayList<SlideModel>() // Create image list



        imageList.add(SlideModel(R.drawable.learns))
        imageList.add(SlideModel(R.drawable.sale))
        imageList.add(SlideModel(R.drawable.onlinecourse))

        val imageSlider = view.findViewById<ImageSlider>(R.id.image_slider)
        imageSlider.setImageList(imageList)

        val card1 = view.findViewById<CardView>(R.id.cardView1)
        val card2 = view.findViewById<CardView>(R.id.cardView2)
        val card3 = view.findViewById<CardView>(R.id.cardView3)
        val card4 = view.findViewById<CardView>(R.id.cardView4)

        card1.setOnClickListener {

            openVideoFragment(cardviewfragment()) }
        card2.setOnClickListener { openVideoFragment(cardviewfragment()) }
        card3.setOnClickListener { openVideoFragment(cardviewfragment()) }
        card4.setOnClickListener { openVideoFragment(cardviewfragment()) }


        return view
    }

    private fun openVideoFragment(fragment: Fragment) {
        fragmentManager?.beginTransaction()?.replace(R.id.fragment_container,fragment)?.addToBackStack(null)?.commit()

    }


}
