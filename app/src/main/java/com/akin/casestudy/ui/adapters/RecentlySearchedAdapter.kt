package com.akin.casestudy.ui.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.akin.casestudy.data.models.LastSearchedModel
import com.akin.casestudy.data.models.mapper.PureCollectionModel
import com.akin.casestudy.databinding.RecentlySearchedItemBinding
import com.akin.casestudy.util.loadString
import com.akin.casestudy.util.loadStringForDetailPage
import com.akin.casestudy.util.makeBigger
import com.akin.casestudy.util.makePlaceHolder

class RecentlySearchedAdapter() :
    RecyclerView.Adapter<RecentlySearchedAdapter.RecentlySearchedViewHolder>() {
    class RecentlySearchedViewHolder(val binding: RecentlySearchedItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

    }

    private var itemsList: List<LastSearchedModel> = mutableListOf()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecentlySearchedViewHolder {
        val binding =
            RecentlySearchedItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RecentlySearchedViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecentlySearchedViewHolder, position: Int) {
        holder.binding.apply {
            val itemListed = itemsList[position]
            rcRecentlySearchedImage.loadStringForDetailPage(
                itemListed.imageUrl?.makeBigger(),
                makePlaceHolder(holder.itemView.context)
            )
            nameText.text = itemListed.collectionName ?: itemListed.artistName
        }
    }

    override fun getItemCount(): Int {
        return itemsList.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun addDataForRc(items: List<LastSearchedModel>) {
        this.itemsList = items
        notifyDataSetChanged()
    }
}