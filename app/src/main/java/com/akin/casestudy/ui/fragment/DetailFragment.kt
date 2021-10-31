package com.akin.casestudy.ui.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.Html
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


class DetailFragment : BaseFragment<FragmentDetailBinding>(FragmentDetailBinding::inflate) {

    private val args: DetailFragmentArgs by navArgs()

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

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

    private fun detailFab() {
        val videoPreviewFragment = VideoPreviewFragment()
        videoPreviewFragment.setStyle(
            DialogFragment.STYLE_NORMAL,
            R.style.ThemeOverlay_Demo_BottomSheetDialog
        )
        val bundle = Bundle()
        bundle.apply {
            putString("url", args.collectionItem.previewUrl)

        }

        videoPreviewFragment.arguments = bundle
        childFragmentManager.let { it1 ->
            videoPreviewFragment.show(it1, "TAG")
        }
    }
    private fun formatDate(returnDate: String): String {
        val readableText = returnDate.split("T")
        return readableText[0]
    }

}