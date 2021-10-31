package com.akin.casestudy.ui.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.Html
import android.transition.TransitionInflater
import android.view.MotionEvent
import android.view.View
import android.view.View.*
import androidx.fragment.app.DialogFragment
import androidx.navigation.fragment.navArgs
import com.akin.casestudy.R
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

    private lateinit var first: MediaItem

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        println(args.collectionItem.primaryGenreName)
        getNecessaryInfo()
        openPreview()


    }

    @SuppressLint("SetTextI18n")
    fun getNecessaryInfo() {

        binding.apply {
            try {
                detailFragmentImageView.loadStringForDetailPage(
                    args.collectionItem.imageUrl?.makeBigger(),
                    makePlaceHolder(requireContext())
                )
            } catch (e: Exception) {
                detailFragmentImageView.loadString(
                    args.collectionItem.imageUrl,
                    makePlaceHolder(requireContext())
                )
            }

            when (args.collectionItem.kind) {
                "feature-movie" -> {
                    collectionNameText.text =
                        args.collectionItem.collectionName ?: args.collectionItem.trackName
                    priceText.text = (args.collectionItem.collectionPrice
                        ?: args.collectionItem.price).toString() + "$"
                    detailText.text = args.collectionItem.longDescription
                    artistNameText.text = args.collectionItem.artistName
                    videoButton.visibility = VISIBLE


                }
                "software" -> {

                    collectionNameText.text =
                        args.collectionItem.trackName ?: args.collectionItem.collectionName
                    priceText.text = (args.collectionItem.price
                        ?: args.collectionItem.formattedPrice).toString() + "$"
                    detailText.text = Html.fromHtml(args.collectionItem.description)
                    artistNameText.text = args.collectionItem.artistName




                }
                "ebook" -> {

                    collectionNameText.text =
                        args.collectionItem.trackName ?: args.collectionItem.artistName
                    priceText.text = (args.collectionItem.price
                        ?: args.collectionItem.formattedPrice).toString() + "$"
                    val readableDesc = Html.fromHtml(args.collectionItem.description)
                    detailText.text = readableDesc
                    artistNameText.text = args.collectionItem.artistName



                }
                else -> {
                    collectionNameText.text =
                        args.collectionItem.collectionName ?: args.collectionItem.trackName
                    priceText.text = (args.collectionItem.collectionPrice
                        ?: args.collectionItem.price).toString() + "$"
                    artistNameText.text = args.collectionItem.artistName
                    detailText.visibility = INVISIBLE
                    introduction.visibility = INVISIBLE



                }

            }
            releaseDateText.text = formatDate(args.collectionItem.releaseDate.toString())

        }
    }

    private fun openPreview() {
        binding.apply {
            videoButton.setOnClickListener {
                detailFab()
            }
        }
    }

    override fun onStop() {
        // binding.videoPlayer.player?.stop()
        super.onStop()
    }

    private fun detailFab() {

        val bottomFragment = VideoPreviewFragment()
        bottomFragment.setStyle(
            DialogFragment.STYLE_NORMAL,
            R.style.ThemeOverlay_Demo_BottomSheetDialog
        )
        val bundle = Bundle()

        bundle.apply {
            putString("url", args.collectionItem.previewUrl)

        }

        bottomFragment.arguments = bundle
        fragmentManager?.let { it1 ->
            bottomFragment.show(it1, "TAG")
        }
    }
    private fun formatDate(returnDate: String): String {
        val readableText = returnDate.split("T")
        return readableText[0]
    }

}