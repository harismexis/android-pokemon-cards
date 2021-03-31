package com.harismexis.pokemon.ui.home.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.harismexis.pokemon.datamodel.remote.PokemonItem
import com.harismexis.pokemon.repository.PokemonRemoteRepository
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