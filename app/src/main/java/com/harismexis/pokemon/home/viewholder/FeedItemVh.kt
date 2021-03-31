package com.harismexis.pokemon.home.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.harismexis.pokemon.uimodel.UiModel
import com.harismexis.pokemon.databinding.VhFeedItemBinding
import com.harismexis.pokemon.extensions.populateWithGlide

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