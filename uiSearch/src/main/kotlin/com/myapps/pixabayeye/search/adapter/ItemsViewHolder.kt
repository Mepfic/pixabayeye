package com.myapps.pixabayeye.search.adapter

import androidx.recyclerview.widget.RecyclerView
import com.myapps.pixabayeye.domain.model.HitModel
import com.myapps.pixabayeye.search.databinding.ItemSearchBinding

internal class ItemsViewHolder(val binding: ItemSearchBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bindTo(item: HitModel?) {
        binding.itemTitle.text = item?.userName
    }
}