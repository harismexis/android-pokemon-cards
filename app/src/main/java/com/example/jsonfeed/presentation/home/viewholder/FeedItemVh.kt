package com.example.jsonfeed.presentation.home.viewholder

import androidx.recyclerview.widget.RecyclerView

import com.example.jsonfeed.databinding.VhFeedItemBinding
import com.example.jsonfeed.domain.LocalFeedItem
import com.example.jsonfeed.framework.extensions.populateWithGlide

class FeedItemVh(
    private var binding: VhFeedItemBinding,
    private var itemClickListener: FeedItemClickListener
) : RecyclerView.ViewHolder(binding.root) {

    interface FeedItemClickListener {
        fun onFeedItemClick(item: LocalFeedItem, position: Int)
    }

    fun bind(
        item: LocalFeedItem,
        position: Int
    ) {
        itemView.context.populateWithGlide(binding.imgContainer, item.imageUrl)
        binding.txtName.text = item.name
        binding.txtMeta.text = item.supertype
        itemView.setOnClickListener {
            itemClickListener.onFeedItemClick(item, position)
        }
    }

    fun unbind() {
        // Release resources, unsubscribe etc
    }

}