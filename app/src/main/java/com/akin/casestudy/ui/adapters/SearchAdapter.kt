package com.akin.casestudy.ui.adapters

import android.annotation.SuppressLint
import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.akin.casestudy.data.models.mapper.PureCollectionModel
import com.akin.casestudy.databinding.CollectionsItemBinding
import com.akin.casestudy.ui.fragment.SearchFragmentDirections
import com.akin.casestudy.util.loadString
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*

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

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        val pureCollectionListed = itemsList[position]
        holder.binding.apply {
            when {
                pureCollectionListed.collectionName.isNullOrEmpty() -> {
                    collectionNameText.text = pureCollectionListed.trackName
                    collectionPriceText.text = pureCollectionListed.price.toString()
                }
                pureCollectionListed.price!!.isNaN() -> {
                    collectionPriceText.text = pureCollectionListed.collectionPrice.toString()
                    collectionNameText.text = pureCollectionListed.collectionName
                }
                else -> {
                    collectionNameText.text = pureCollectionListed.collectionName
                    collectionPriceText.text = pureCollectionListed.collectionPrice.toString()+"$"
                }
            }

            val readableDate = formatDate(pureCollectionListed.releaseDate.toString())
            releaseDateText.text = readableDate
            cardImage.loadString(pureCollectionListed.imageUrl)
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