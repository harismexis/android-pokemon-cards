package com.harismexis.pokemon.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.harismexis.pokemon.home.viewholder.FeedItemVh
import com.harismexis.pokemon.uimodel.UiModel
import com.harismexis.pokemon.databinding.VhFeedItemBinding

class HomeAdapter(
    private var models: List<UiModel>,
    private var clickListener: FeedItemVh.FeedItemClickListener
) : RecyclerView.Adapter<FeedItemVh>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): FeedItemVh {
        return FeedItemVh(
            VhFeedItemBinding.inflate(LayoutInflater.from(parent.context), parent, false),
            clickListener
        )
    }

    override fun onBindViewHolder(
        viewHolder: FeedItemVh,
        position: Int
    ) {
        viewHolder.bind(models[position], position)
    }

    override fun getItemCount(): Int {
        return models.size
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun onViewDetachedFromWindow(holder: FeedItemVh) {
        super.onViewDetachedFromWindow(holder)
        holder.unbind()
    }

}