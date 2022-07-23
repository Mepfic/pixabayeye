package com.myapps.pixabayeye.search.adapter

import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.request.CachePolicy
import coil.transform.RoundedCornersTransformation
import com.google.android.material.chip.Chip
import com.myapps.pixabayeye.common.R
import com.myapps.pixabayeye.domain.model.HitModel
import com.myapps.pixabayeye.domain.util.tagsToList
import com.myapps.pixabayeye.search.databinding.ItemSearchBinding

class ItemsViewHolder(val binding: ItemSearchBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bindTo(item: HitModel?) {
        binding.nameText.text = item?.userName
        binding.previewImage.load(item?.previewUrl) {
            crossfade(true)
            diskCachePolicy(CachePolicy.ENABLED)
            placeholder(R.drawable.ic_icon_placeholder)
            transformations(
                RoundedCornersTransformation(
                    binding.root.context.resources
                        .getDimensionPixelSize(R.dimen.preview_icon_corner_radius)
                        .toFloat()
                )
            )
        }
        binding.tagsChipGroup.removeAllViews()
        item?.tags?.tagsToList()?.forEach {
            Chip(binding.root.context).apply {
                text = it
                binding.tagsChipGroup.addView(this)
                isClickable = false
            }
        }
    }
}
