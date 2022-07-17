package com.myapps.pixabayeye.search.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.myapps.pixabayeye.domain.model.HitModel
import com.myapps.pixabayeye.search.databinding.ItemSearchBinding

internal class ImageAdapter(private val onClickCallback: (itemId: Long) -> Unit) :
    PagingDataAdapter<HitModel, ItemsViewHolder>(diffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ItemsViewHolder(
            ItemSearchBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )

    override fun onBindViewHolder(holder: ItemsViewHolder, position: Int) {
        getItem(position)?.let { item ->
            holder.bindTo(item)
            holder.binding.root.setOnClickListener { onClickCallback(item.imageId) }
        }
    }

    companion object {
        val diffCallback = object : DiffUtil.ItemCallback<HitModel>() {
            override fun areItemsTheSame(oldItem: HitModel, newItem: HitModel): Boolean =
                oldItem.imageId == newItem.imageId

            override fun areContentsTheSame(oldItem: HitModel, newItem: HitModel): Boolean =
                oldItem == newItem
        }
    }
}