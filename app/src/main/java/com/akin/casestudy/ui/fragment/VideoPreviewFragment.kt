package com.akin.casestudy.ui.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.akin.casestudy.R
import com.akin.casestudy.databinding.FragmentVideoPreviewBinding
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.SimpleExoPlayer


class VideoPreviewFragment : DialogFragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    private var _binding: FragmentVideoPreviewBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentVideoPreviewBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        println(requireArguments().getString("url").toString())
        playPreview(requireArguments().getString("url").toString())
    }

    @SuppressLint("ClickableViewAccessibility")
    fun playPreview(url: String) {

        val player = SimpleExoPlayer.Builder(requireContext()).build()

        val first =
            MediaItem.fromUri(url)
        binding.apply {
            videoPlayer.player = player
            player.setMediaItem(first)
            player.prepare()
            player.play()

        }

    }

    override fun onStop() {
        binding.videoPlayer.player?.stop()
        super.onStop()
    }

}