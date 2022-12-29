package com.myapps.pixabayeye.search.adapter

import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.request.CachePolicy
import coil.transform.RoundedCornersTransformation
import com.google.android.material.chip.Chip
import com.myapps.pixabayeye.common.R
import com.myapps.pixabayeye.search.databinding.ItemSearchBinding
import com.myapps.pixabayeye.search.state.SearchItemState

class ItemsViewHolder(val binding: ItemSearchBinding, val onItemClick: (tag: String) -> Unit) :
    RecyclerView.ViewHolder(binding.root) {
    fun bindTo(item: SearchItemState?) {
        binding.nameText.text = item?.userName
        binding.previewImage.load(item?.previewUrl) {
            crossfade(true)
            diskCachePolicy(CachePolicy.ENABLED)
            placeholder(R.drawable.ic_icon_placeholder)
            error(R.drawable.ic_icon_placeholder)
            transformations(
                RoundedCornersTransformation(
                    binding.root.context.resources
                        .getDimensionPixelSize(R.dimen.preview_icon_corner_radius)
                        .toFloat()
                )
            )
        }
        binding.tagsChipGroup.removeAllViews()
        item?.tags?.forEach { tag ->
            Chip(binding.root.context).apply {
                text = tag
                binding.tagsChipGroup.addView(this)
                setOnClickListener { onItemClick(tag) }
            }
        }
    }
}
