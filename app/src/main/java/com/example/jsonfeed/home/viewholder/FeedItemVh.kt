package com.example.jsonfeed.home.viewholder

import androidx.recyclerview.widget.RecyclerView

import com.example.jsonfeed.databinding.VhFeedItemBinding
import com.example.jsonfeed.extensions.populateWithGlide
import com.example.jsonfeed.uimodel.UiModel

class FeedItemVh(
    private var binding: VhFeedItemBinding,
    private var itemClickListener: FeedItemClickListener
) : RecyclerView.ViewHolder(binding.root) {

    interface FeedItemClickListener {
        fun onFeedItemClick(item: UiModel, position: Int)
    }

    fun bind(
        item: UiModel,
        position: Int
    ) {
        itemView.context.populateWithGlide(binding.imgContainer, item.imageUrl)
        binding.txtTitle.text = item.name
        binding.txtMeta.text = item.supertype
        itemView.setOnClickListener {
            itemClickListener.onFeedItemClick(item, position)
        }
    }

    fun unbind() {
        // Release resources, unsubscribe etc
    }

}