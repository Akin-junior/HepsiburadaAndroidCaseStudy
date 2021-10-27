package com.akin.casestudy.ui.adapters

import android.os.Parcel
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.akin.casestudy.data.models.mapper.PureCollectionModel
import com.akin.casestudy.databinding.CollectionsItemBinding

//import com.akin.casestudy.ui.adapters.TestAdapter.CustomViewHolder

//class TestAdapter : ListAdapter<PureCollectionModel,CustomViewHolder>(DIFF) {
//
//    var itemClickListener: (id: Int) -> Unit = {}
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
//        val inflater = LayoutInflater.from(parent.context)
//        val binding = CollectionsItemBinding.inflate(inflater, parent, false)
//        return CustomViewHolder(binding)
//    }
//
//    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
//        holder.bind(getItem(position))
//    }
//
//    inner class CustomViewHolder(private val binding: CollectionsItemBinding) :
//        RecyclerView.ViewHolder(binding.root) {
//        fun bind(item: PureCollectionModel) {
//            binding.apply {
////                photo.load(item.image)
////                name.text = item.name
////                status.text = item.status
//                root.setOnClickListener {
//                    itemClickListener(item.collectionPrice.toInt())
//                }
//            }
//        }
//    }
//
//    object DIFF : DiffUtil.ItemCallback<PureCollectionModel>() {
//        override fun areItemsTheSame(
//            oldItem: PureCollectionModel,
//            newItem: PureCollectionModel
//        ): Boolean {
//            return  false
//          //  return oldItem.id == newItem.id
//        }
//
//
//
//        override fun areContentsTheSame(
//            oldItem: PureCollectionModel,
//            newItem: PureCollectionModel
//        ): Boolean {
//            return oldItem.toString() == newItem.toString()
//        }
//    }
//
//}