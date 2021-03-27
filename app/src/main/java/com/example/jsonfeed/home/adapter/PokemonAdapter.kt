package com.example.jsonfeed.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.example.jsonfeed.databinding.VhPokemonItemBinding
import com.example.jsonfeed.datamodel.PokemonItem
import com.example.jsonfeed.home.viewholder.PokemonItemVh

class PokemonAdapter(
    private var itemClickListener: PokemonItemVh.PokemonItemClickListener
) : PagingDataAdapter<PokemonItem, PokemonItemVh>(POKEMON_CARD_COMPARATOR) {

    companion object {

        private val POKEMON_CARD_COMPARATOR = object : DiffUtil.ItemCallback<PokemonItem>() {

            override fun areItemsTheSame(
                oldItem: PokemonItem,
                newItem: PokemonItem
            ): Boolean = oldItem.id == newItem.id

            override fun areContentsTheSame(
                oldItem: PokemonItem,
                newItem: PokemonItem
            ): Boolean = oldItem == newItem
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PokemonItemVh {
        return PokemonItemVh(
            VhPokemonItemBinding.inflate(LayoutInflater.from(parent.context), parent, false),
            itemClickListener
        )
    }

    override fun onBindViewHolder(
        viewHolder: PokemonItemVh,
        position: Int
    ) {
        val item = getItem(position)
        item?.let {
            viewHolder.bind(it, position)
        }
    }

}