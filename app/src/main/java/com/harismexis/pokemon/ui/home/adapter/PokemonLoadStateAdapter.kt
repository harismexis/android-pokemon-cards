package com.harismexis.pokemon.ui.home.adapter

import android.view.ViewGroup
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import com.harismexis.pokemon.ui.home.viewholder.PokemonLoadStateViewHolder

class PokemonLoadStateAdapter(
    private val retry: () -> Unit
) : LoadStateAdapter<PokemonLoadStateViewHolder>() {

    override fun onBindViewHolder(
        holder: PokemonLoadStateViewHolder,
        loadState: LoadState
    ) {
        holder.bind(loadState)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        loadState: LoadState
    ): PokemonLoadStateViewHolder {
        return PokemonLoadStateViewHolder.create(parent, retry)
    }
}
