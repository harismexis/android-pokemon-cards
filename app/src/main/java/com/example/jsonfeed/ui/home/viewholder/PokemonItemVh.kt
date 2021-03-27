package com.example.jsonfeed.ui.home.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.example.jsonfeed.R
import com.example.jsonfeed.databinding.VhPokemonItemBinding
import com.example.jsonfeed.datamodel.remote.PokemonItem
import com.example.jsonfeed.extensions.populateWithGlide

class PokemonItemVh(
    private var binding: VhPokemonItemBinding,
    private var itemClickListener: PokemonItemClickListener
) : RecyclerView.ViewHolder(binding.root) {

    interface PokemonItemClickListener {
        fun onPokemonItemClick(item: PokemonItem, position: Int)
    }

    fun bind(
        item: PokemonItem?,
        position: Int
    ) {
        if (item == null) bindMissingItem()
        else bindPokemon(item, position)
    }

    private fun bindMissingItem() {
        itemView.context.populateWithGlide(binding.imgContainer, "")
        binding.txtName.text = itemView.context.getString(R.string.missing_value)
        binding.txtMeta.text = itemView.context.getString(R.string.missing_value)
    }

    private fun bindPokemon(item: PokemonItem, position: Int) {
        itemView.context.populateWithGlide(binding.imgContainer, item.imageUrl)
        binding.txtName.text = item.name
        binding.txtMeta.text = item.supertype
        itemView.setOnClickListener {
            itemClickListener.onPokemonItemClick(item, position)
        }
    }


}