package com.myapps.pixabayeye.search.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.myapps.pixabayeye.domain.model.HitModel
import com.myapps.pixabayeye.search.databinding.ItemSearchBinding

class ImageAdapter(private val onClickCallback: (itemId: Long) -> Unit) :
    RecyclerView.Adapter<ImageAdapter.ItemsViewHolder>() {

    private var items: List<HitModel> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ItemsViewHolder(
            ItemSearchBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )

    override fun onBindViewHolder(holder: ItemsViewHolder, position: Int) {
        with(holder) {
            with(items[position]) {
//                binding.appCompatImageView.setImageResource(getImageByType(type))
                binding.itemTitle.text = userName
                binding.root.setOnClickListener { onClickCallback(imageId) }
            }
        }
    }

    override fun getItemCount() = items.size

    fun submitList(list: List<HitModel>) {
        items = list
        notifyDataSetChanged()
    }


    inner class ItemsViewHolder(val binding: ItemSearchBinding) :
        RecyclerView.ViewHolder(binding.root)
}