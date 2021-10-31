package com.akin.casestudy.ui.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.akin.casestudy.data.models.mapper.PureCollectionModel
import com.akin.casestudy.databinding.CollectionsItemBinding
import com.akin.casestudy.util.loadString
import com.akin.casestudy.util.makePlaceHolder

class SearchAdapter(

    var clickListener: (data: PureCollectionModel) -> Unit = {}
) :
    RecyclerView.Adapter<SearchAdapter.SearchViewHolder>() {
    class SearchViewHolder(val binding: CollectionsItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

    }

    private var itemsList: List<PureCollectionModel> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        val binding =
            CollectionsItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return SearchViewHolder(binding)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        val pureCollectionListed = itemsList[position]
        holder.binding.apply {
            when (pureCollectionListed.kind) {
                "feature-movie" -> {
                    collectionNameText.text =
                        pureCollectionListed.collectionName ?: pureCollectionListed.trackName
                    collectionPriceText.text = pureCollectionListed.collectionPrice.toString() + "$"

                }
                "software" -> {
                    collectionNameText.text =
                        pureCollectionListed.trackName ?: pureCollectionListed.artistName
                    collectionPriceText.text = (pureCollectionListed.price
                        ?: pureCollectionListed.formattedPrice).toString() + "$"
                }
                "ebook" -> {
                    collectionNameText.text =
                        pureCollectionListed.trackName ?: pureCollectionListed.artistName
                    collectionPriceText.text = (pureCollectionListed.price
                        ?: pureCollectionListed.formattedPrice).toString() + "$"
                }
                else -> {
                    collectionNameText.text = pureCollectionListed.collectionName
                    collectionPriceText.text = pureCollectionListed.collectionPrice.toString() + "$"
                }
            }


            val readableDate = formatDate(pureCollectionListed.releaseDate.toString())
            releaseDateText.text = readableDate
            cardImage.loadString(
                pureCollectionListed.imageUrl,
                makePlaceHolder(holder.binding.root.context)
            )

            cardViewCollections.setOnClickListener {
                clickListener(pureCollectionListed)

            }
        }
    }

    override fun getItemCount(): Int {
        return itemsList.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun loadCollectionsData(items: List<PureCollectionModel>) {
        this.itemsList = items
        notifyDataSetChanged()
    }


    private fun formatDate(returnDate: String): String {
        val readableText = returnDate.split("T")
        return readableText[0]
    }
}