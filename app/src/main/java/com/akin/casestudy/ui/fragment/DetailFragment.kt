package com.akin.casestudy.ui.fragment

import android.annotation.SuppressLint
import android.media.session.MediaController
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.akin.casestudy.R
import com.akin.casestudy.databinding.FragmentDetailBinding
import com.akin.casestudy.ui.fragment.basefragment.BaseFragment
import com.akin.casestudy.util.loadString
import com.akin.casestudy.util.loadStringForDetailPage


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
                detailFragmentImageView.loadStringForDetailPage(biggerImage)

            } catch (e: Exception) {
                detailFragmentImageView.loadString(args.collectionItem.imageUrl)
            }

            when {
                args.collectionItem.collectionPrice!!.isNaN() || args.collectionItem.collectionPrice == 0.0 -> {
                    priceText.text = args.collectionItem.price.toString() + " $"
                    nameText.text = args.collectionItem.artistName
                    detailText.text = args.collectionItem.description
                }
                args.collectionItem.trackName.isNullOrEmpty() -> {
                    nameText.text = args.collectionItem.collectionName
                    priceText.text = args.collectionItem.collectionPrice.toString() + " $"
                }
                args.collectionItem.description.isNullOrEmpty() -> {
                    detailText.text = args.collectionItem.shortDescription
                    priceText.text = args.collectionItem.collectionPrice.toString() + " $"
                    nameText.text = args.collectionItem.collectionName

                }
                else -> {
                    priceText.text = args.collectionItem.collectionPrice.toString() + " $"
                    nameText.text = args.collectionItem.collectionName
                    detailText.text = args.collectionItem.description
                }
            }

        }
    }

}