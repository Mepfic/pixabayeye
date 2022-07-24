package com.myapps.pixabayeye.search.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.myapps.pixabayeye.search.databinding.ItemSearchBinding
import com.myapps.pixabayeye.search.state.SearchItemState

internal class ImageAdapter(
    private val onItemClickCallback: (itemId: Long) -> Unit,
    private val onTagClickCallback: (tag: String) -> Unit,
) :
    PagingDataAdapter<SearchItemState, ItemsViewHolder>(diffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ItemsViewHolder(
            ItemSearchBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        ) { onTagClickCallback(it) }

    override fun onBindViewHolder(holder: ItemsViewHolder, position: Int) {
        getItem(position)?.let { item ->
            holder.bindTo(item)
            holder.binding.root.setOnClickListener { onItemClickCallback(item.imageId) }
        }
    }

    companion object {
        val diffCallback = object : DiffUtil.ItemCallback<SearchItemState>() {
            override fun areItemsTheSame(oldItem: SearchItemState, newItem: SearchItemState) =
                oldItem.imageId == newItem.imageId

            override fun areContentsTheSame(oldItem: SearchItemState, newItem: SearchItemState) =
                oldItem == newItem
        }
    }
}
