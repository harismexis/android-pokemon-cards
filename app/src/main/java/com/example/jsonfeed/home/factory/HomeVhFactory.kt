package com.example.jsonfeed.home.factory

import android.view.LayoutInflater
import com.example.jsonfeed.databinding.VhFeedItemBinding
import com.example.jsonfeed.home.adapter.HomeAdapter
import com.example.jsonfeed.home.viewholder.FeedItemVh

fun createHomeViewHolder(
    inflater: LayoutInflater,
    clickListener: FeedItemVh.FeedItemClickListener
): FeedItemVh {
    return FeedItemVh(
        VhFeedItemBinding.inflate(inflater),
        clickListener
    )

}
