package com.example.navdrawerscratch


import android.net.Uri
import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.MediaController
import android.widget.TextView
import android.widget.VideoView

class videofragment : Fragment() {
    private lateinit var videoView: VideoView
    private var videoUrl: String? = null
    private lateinit var textview: TextView
    private lateinit var description: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_videofragment, container, false)
        videoView = view.findViewById(R.id.videoView)
        textview = view.findViewById(R.id.videotopic)
        description = view.findViewById(R.id.description)
        description.setOnClickListener {
            toggleReadMore(description)
        }
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val args = arguments
        if (args != null && args.containsKey("data") && args.containsKey("desc")) {
            val inputData = args.getString("data")
            val descData = args.getString("desc")
            textview.text = inputData
            description.text = descData
            videoUrl = args.getString("videoUrl")
            if (!videoUrl.isNullOrEmpty()) {
                playVideo(videoUrl!!)
            }
        }
    }

    private fun playVideo(videoUrl: String) {
        try {
            videoView.setVideoURI(Uri.parse(videoUrl))
            val mediaController = MediaController(requireContext())
            mediaController.setAnchorView(videoView)
            videoView.setMediaController(mediaController)
            videoView.setOnPreparedListener { mp ->
                mp.start()
            }
        } catch (e: Exception) {
            e.printStackTrace()
            // Handle exception
        }
    }

    private fun toggleReadMore(textView: TextView) {
        if (textView.maxLines == 3) {
            // Expand text
            textView.maxLines = Int.MAX_VALUE}
        else{
            textView.maxLines=3
        }
    }
}
