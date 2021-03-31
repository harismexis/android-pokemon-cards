package com.harismexis.pokemon.ui.home.viewholder

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.recyclerview.widget.RecyclerView
import com.harismexis.pokemon.R
import com.harismexis.pokemon.databinding.PokemonLoadStateItemBinding

class PokemonLoadStateViewHolder(
    private val binding: PokemonLoadStateItemBinding,
    retry: () -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    init {
        binding.retryButton.also {
            it.setOnClickListener { retry.invoke() }
        }
    }

    fun bind(loadState: LoadState) {
        if (loadState is LoadState.Error) {
            binding.errorMsg.text = loadState.error.localizedMessage
        }
        binding.progressBar.isVisible = loadState is LoadState.Loading
        binding.retryButton.isVisible = loadState is LoadState.Error
        binding.errorMsg.isVisible = loadState is LoadState.Error
    }

    companion object {
        fun create(parent: ViewGroup, retry: () -> Unit): PokemonLoadStateViewHolder {
            val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.pokemon_load_state_item, parent, false)
            val binding = PokemonLoadStateItemBinding.bind(view)
            return PokemonLoadStateViewHolder(binding, retry)
        }
    }
}
