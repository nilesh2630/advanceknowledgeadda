package com.example.navdrawerscratch

import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.cardview.widget.CardView


class cardviewfragment : Fragment() {

lateinit var editText: TextView
lateinit var string: String
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view= inflater.inflate(R.layout.fragment_cardviewfragment, container, false)
        val btn1 = view.findViewById<Button>(R.id.buttonPlay1)
        val btn2 = view.findViewById<Button>(R.id.buttonPlay2)
        val btn3 = view.findViewById<Button>(R.id.buttonPlay3)
        val btn4 = view.findViewById<Button>(R.id.buttonPlay4)
        val btn5 = view.findViewById<Button>(R.id.buttonPlay5)
        val btn6 = view.findViewById<Button>(R.id.buttonPlay6)

        btn1.setOnClickListener {
             editText= view.findViewById(R.id.textTitle1)
             string= getString(R.string.section_one)
            openVideoFragment("learnfraction") }
        btn2.setOnClickListener {
            editText=view.findViewById(R.id.textTitle2)
             string= getString(R.string.section_two)
            openVideoFragment("learnaddition") }
        btn3.setOnClickListener {
            editText=view.findViewById(R.id.textTitle3)
            string= getString(R.string.section_three)
            openVideoFragment("learningnumber") }
        btn4.setOnClickListener {
            editText=view.findViewById(R.id.textTitle4)
            string= getString(R.string.section_four)
            openVideoFragment("learnfraction") }
        btn5.setOnClickListener {
            editText=view.findViewById(R.id.textTitle5)
            string= getString(R.string.section_five)
            openVideoFragment("learnaddition") }
        btn6.setOnClickListener {
            editText=view.findViewById(R.id.textTitle6)
            string= getString(R.string.section_six)
            openVideoFragment("learningnumber") }
        return view
    }

    private fun openVideoFragment(videoUr: String) {
        val input=editText.text.toString()
        val bundle=Bundle()
        bundle.putString("data",input)
        bundle.putString("desc",string)
        val videoPath = "android.resource://${requireActivity().packageName}/raw/$videoUr"
        bundle.putString("videoUrl",videoPath)
        val videoFragment = videofragment()
        videoFragment.arguments=bundle

        fragmentManager?.beginTransaction()?.replace(R.id.fragment_container, videoFragment)?.addToBackStack(null)?.commit()
    }


}

