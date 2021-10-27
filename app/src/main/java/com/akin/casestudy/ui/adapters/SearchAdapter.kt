package com.akin.casestudy.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.akin.casestudy.data.models.mapper.PureCollectionModel
import com.akin.casestudy.databinding.CollectionsItemBinding
import com.akin.casestudy.databinding.FragmentSearchBinding
import com.akin.casestudy.ui.fragment.SearchFragmentDirections
import com.akin.casestudy.util.loadString

class SearchAdapter(private val pureCollectionList: List<PureCollectionModel>) :
    RecyclerView.Adapter<SearchAdapter.SearchViewHolder>() {
    class SearchViewHolder(val binding: CollectionsItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        val binding =
            CollectionsItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return SearchViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        val pureCollectionListed = pureCollectionList[position]
        holder.binding.apply {
            collectionNameText.text = pureCollectionListed.collectionName
            collectionPriceText.text = pureCollectionListed.collectionPrice.toString()
            releaseDateText.text = pureCollectionListed.releaseDate
            cardImage.loadString(pureCollectionListed.imageUrl)
            cardViewCollections.setOnClickListener {
                val action = SearchFragmentDirections.actionSearchFragmentToDetailFragment(pureCollectionList[position])
                it.findNavController().navigate(action)
            }
        }
    }

    override fun getItemCount(): Int {
        return pureCollectionList.size
    }
}