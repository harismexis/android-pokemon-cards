package com.example.jsonfeed.home.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.jsonfeed.datamodel.PokemonItem
import com.example.jsonfeed.localdb.repository.LocalRepository
import com.example.jsonfeed.repository.PokemonRemoteRepository
import com.example.jsonfeed.util.network.ConnectivityMonitor
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PokemonViewModel @Inject constructor(
    var pokemonRemote: PokemonRemoteRepository,
    var localRepo: LocalRepository,
    var connectivity: ConnectivityMonitor,
) : ViewModel() {

    fun getPokemonCardsStream(): Flow<PagingData<PokemonItem>> {
        return pokemonRemote.getPokemonCardsStream()
            .cachedIn(viewModelScope)
    }
}
