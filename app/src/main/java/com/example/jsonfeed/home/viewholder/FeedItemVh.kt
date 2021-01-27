package com.example.jsonfeed.home.viewholder

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.jsonfeed.databinding.VhFeedItemBinding
import com.example.jsonfeed.model.FeedItem

class FeedItemVh(
    private var binding: VhFeedItemBinding,
    private var itemClickListener: FeedItemClickListener
) : RecyclerView.ViewHolder(binding.root) {

    private val tag = FeedItemVh::class.qualifiedName

    interface FeedItemClickListener {
        fun onFeedItemClick(item: FeedItem, position: Int)
    }

    fun bind(
        item: FeedItem,
        position: Int
    ) {
        // populateImage(item.imgSrc)
        binding.txtTitle.text = item.name
        binding.txtMeta.text = item.description
        binding.root.setOnClickListener {
            itemClickListener.onFeedItemClick(item, position)
        }
    }

    fun unbind() {}

    private fun populateImage(url: String?) {
        binding.imgContainer.layout(0, 0, 0, 0)
        Glide.with(itemView.context)
            .load(Uri.parse(url))
            .override(binding.imgContainer.height)
            .error(ColorDrawable(Color.BLACK))
            .diskCacheStrategy(DiskCacheStrategy.NONE)
            .into(binding.imgContainer)
    }
}