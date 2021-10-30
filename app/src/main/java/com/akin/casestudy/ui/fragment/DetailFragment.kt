package com.akin.casestudy.ui.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.Html
import android.view.MotionEvent
import android.view.View
import android.view.View.GONE
import androidx.navigation.fragment.navArgs
import com.akin.casestudy.databinding.FragmentDetailBinding
import com.akin.casestudy.ui.fragment.basefragment.BaseFragment
import com.akin.casestudy.util.loadString
import com.akin.casestudy.util.loadStringForDetailPage
import com.akin.casestudy.util.makeBigger
import com.akin.casestudy.util.makePlaceHolder
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.SimpleExoPlayer


class DetailFragment : BaseFragment<FragmentDetailBinding>(FragmentDetailBinding::inflate) {

    private val args: DetailFragmentArgs by navArgs()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        println(args.collectionItem.primaryGenreName)
        getNecessaryInfo()
        //detailFab()
        playPreview()
    }

    @SuppressLint("SetTextI18n")
    fun getNecessaryInfo() {

        binding.apply {
            try {
                detailFragmentImageView.loadStringForDetailPage(
                    args.collectionItem.imageUrl?.makeBigger(),
                    makePlaceHolder(requireContext())

                )
                videoPlayer.visibility = GONE
            } catch (e: Exception) {
                detailFragmentImageView.loadString(
                    args.collectionItem.imageUrl,
                    makePlaceHolder(requireContext())
                )
            }
            when (args.collectionItem.kind) {
                "feature-movie" -> {
                    nameText.text =
                        args.collectionItem.collectionName ?: args.collectionItem.trackName
                    priceText.text = (args.collectionItem.collectionPrice
                        ?: args.collectionItem.price).toString() + "$"
                    detailText.text = args.collectionItem.longDescription

                }
                "software" -> {
                    nameText.text =
                        args.collectionItem.trackName ?: args.collectionItem.artistName
                    priceText.text = (args.collectionItem.price
                        ?: args.collectionItem.formattedPrice).toString() + "$"
                    detailText.text = Html.fromHtml(args.collectionItem.description)
                    fabPlay.visibility = GONE
                }
                "ebook" -> {
                    nameText.text =
                        args.collectionItem.trackName ?: args.collectionItem.artistName
                    priceText.text = (args.collectionItem.price
                        ?: args.collectionItem.formattedPrice).toString() + "$"
                    val readableDesc = Html.fromHtml(args.collectionItem.description)
                    detailText.text = readableDesc
                    fabPlay.visibility = GONE
                }
                else -> {
                    nameText.text =
                        args.collectionItem.collectionName ?: args.collectionItem.trackName
                    priceText.text = (args.collectionItem.collectionPrice
                        ?: args.collectionItem.price).toString() + "$"
                    detailText.text = args.collectionItem.primaryGenreName.toString()

                }
            }
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    fun playPreview() {
        var isClick = false
        val first =
            MediaItem.fromUri(args.collectionItem.previewUrl.toString())
        val player = SimpleExoPlayer.Builder(requireContext()).build()
        binding.apply {
            videoPlayer.player = player
            player.setMediaItem(first)
            player.prepare()
            fabPlay.setOnTouchListener { view, motionEvent ->
                if (motionEvent.action == MotionEvent.ACTION_UP) {
                    isClick = if (!isClick) {
                        player.play()
                        !isClick
                    } else {
                        player.pause()
                        false
                    }

                }
                false
            }

        }

    }

    override fun onStop() {
        binding.videoPlayer.player?.stop()
        super.onStop()
    }

}