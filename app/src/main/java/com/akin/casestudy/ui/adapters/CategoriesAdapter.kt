package com.akin.casestudy.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.akin.casestudy.R
import com.akin.casestudy.data.models.CategoriesModel
import com.akin.casestudy.databinding.CategoriesItemBinding
import com.akin.casestudy.interfaces.ICategoriesOnClick
import com.akin.casestudy.util.Statics.Companion.LIMIT
import com.akin.casestudy.util.loadInt


class CategoriesAdapter(
    private val categoriesList: List<CategoriesModel>,
    val clickListener: (categories: String) -> Unit = {}
) :
    RecyclerView.Adapter<CategoriesAdapter.CategoriesViewHolder>() {
    private var listener: ICategoriesOnClick? = null
    private var selectedItem = -1

    inner class CategoriesViewHolder(val binding: CategoriesItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoriesViewHolder {
        val binding =
            CategoriesItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CategoriesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CategoriesViewHolder, position: Int) {
        val categorizedList = categoriesList[position]

        holder.binding.apply {
            categoriesItemTitle.text = categorizedList.shownName
            categoriesItemImage.loadInt(categorizedList.image)
        }
        when (position) {
            selectedItem -> {
                holder.binding.linearLayout.setBackgroundResource(R.drawable.categories_bg)
                holder.binding.linearLayout.animate().scaleX(1.1f)
                holder.binding.linearLayout.animate().scaleY(1.1f)


            }
            -1 -> {
                holder.binding.linearLayout.setBackgroundResource(R.drawable.categories_notseleceted_bg)
            }
            else -> {
                holder.binding.linearLayout.setBackgroundResource(R.drawable.categories_notseleceted_bg)
                holder.binding.linearLayout.animate().scaleX(1f)
                holder.binding.linearLayout.animate().scaleY(1f)
            }
        }
        holder.binding.linearLayout.setOnClickListener {
            if (selectedItem!=position){
                clickListener(categorizedList.name)
                LIMIT = 20
            }
            selectedItem = position
            listener?.let {
                listener?.onClick(position)
            }
            notifyDataSetChanged()
        }


    }

    override fun getItemCount(): Int {
        return categoriesList.size
    }
}