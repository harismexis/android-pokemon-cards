package com.example.jsonfeed.ui.home.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.jsonfeed.model.PokemonItem
import com.example.jsonfeed.repository.PokemonRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class HomeVm @Inject constructor(
    var pokemonRepository: PokemonRepository
) : ViewModel() {

    fun getPokemonCardsStream(): Flow<PagingData<PokemonItem>> {
        return pokemonRepository.getPokemonCardsStream()
            .cachedIn(viewModelScope)
    }

}