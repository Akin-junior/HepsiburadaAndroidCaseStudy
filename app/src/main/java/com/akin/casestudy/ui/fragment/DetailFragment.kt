package com.akin.casestudy.ui.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.Html
import android.view.View
import androidx.core.text.htmlEncode
import androidx.core.text.parseAsHtml
import androidx.navigation.fragment.navArgs
import com.akin.casestudy.databinding.FragmentDetailBinding
import com.akin.casestudy.ui.fragment.basefragment.BaseFragment
import com.akin.casestudy.util.loadString
import com.akin.casestudy.util.loadStringForDetailPage
import com.akin.casestudy.util.makePlaceHolder


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
    }

    @SuppressLint("SetTextI18n")
    fun getNecessaryInfo() {
        binding.apply {
            //we convert to image to bigger
            try {
                val biggerImage =
                    args.collectionItem.imageUrl?.replace("100x100bb.jpg", "500x500bb.jpg")
                detailFragmentImageView.loadStringForDetailPage(biggerImage, makePlaceHolder(requireContext()))

            } catch (e: Exception) {
                detailFragmentImageView.loadString(args.collectionItem.imageUrl, makePlaceHolder(requireContext()))
            }
            when (args.collectionItem.kind) {
                "feature-movie" -> {
                    nameText.text =
                        args.collectionItem.collectionName ?:  args.collectionItem.trackName
                    priceText.text = (args.collectionItem.collectionPrice ?: args.collectionItem.price).toString()+ "$"
                    detailText.text = args.collectionItem.longDescription

                }
                "software" -> {
                    nameText.text =
                        args.collectionItem.trackName ?:  args.collectionItem.artistName
                    priceText.text = (args.collectionItem.price ?: args.collectionItem.formattedPrice).toString()+ "$"
                    detailText.text = args.collectionItem.description
                }
                "ebook" -> {
                    nameText.text =
                        args.collectionItem.trackName ?:  args.collectionItem.artistName
                    priceText.text = (args.collectionItem.price ?: args.collectionItem.formattedPrice).toString()+ "$"
                    val readableDesc = Html.fromHtml(args.collectionItem.description)
                    detailText.text = readableDesc
                }
                else -> {
                    nameText.text =
                        args.collectionItem.collectionName ?:  args.collectionItem.trackName
                    priceText.text = (args.collectionItem.collectionPrice ?: args.collectionItem.price).toString()+ "$"
                    detailText.text = args.collectionItem.primaryGenreName.toString()

                }
            }
        }
    }

}