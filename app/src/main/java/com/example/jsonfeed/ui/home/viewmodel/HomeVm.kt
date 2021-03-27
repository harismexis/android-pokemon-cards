package com.example.jsonfeed.ui.home.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.jsonfeed.datamodel.remote.PokemonItem
import com.example.jsonfeed.repository.PokemonRemoteRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class HomeVm @Inject constructor(
    var pokemonRemote: PokemonRemoteRepository
) : ViewModel() {

    fun getPokemonCardsStream(): Flow<PagingData<PokemonItem>> {
        return pokemonRemote.getPokemonCardsStream()
            .cachedIn(viewModelScope)
    }

}