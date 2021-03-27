package com.example.jsonfeed.home.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jsonfeed.extensions.getErrorMessage
import com.example.jsonfeed.extensions.toUiModels
import com.example.jsonfeed.localdb.repository.LocalRepository
import com.example.jsonfeed.repository.PokemonRemoteRepository
import com.example.jsonfeed.uimodel.UiModel
import com.example.jsonfeed.util.functional.Action1
import com.example.jsonfeed.util.network.ConnectivityMonitor
import kotlinx.coroutines.launch
import javax.inject.Inject

class HomeVm @Inject constructor(
    var pokemonRemote: PokemonRemoteRepository,
    var localRepo: LocalRepository,
    var connectivity: ConnectivityMonitor,
) : ViewModel() {

    private val TAG = HomeVm::class.qualifiedName

    private val mModels = MutableLiveData<List<UiModel>>()
    val models: LiveData<List<UiModel>>
        get() = mModels

    fun bind() {
        if (connectivity.isOnline()) {
            fetchRemoteFeed()
        } else {
            fetchLocalFeed()
        }
    }

    fun refresh(callback: Action1<Boolean>) {
        val canRefresh = connectivity.isOnline()
        callback.call(canRefresh)
        if (canRefresh) {
            fetchRemoteFeed()
        }
    }

    private fun fetchRemoteFeed() {
        viewModelScope.launch {
            try {
//                val response = pokemonRemote.getJsonFeed()
//                val localItems = response.toLocalItems()
//                val uiModels = localItems.toUiModels()
//                mModels.value = uiModels
//                localRepo.insertItems(localItems)
            } catch (e: Exception) {
                Log.d(TAG, e.getErrorMessage())
            }
        }
    }

    private fun fetchLocalFeed() {
        viewModelScope.launch {
            try {
                val localItems = localRepo.getAllItems()
                val uiModels = localItems.toUiModels()
                mModels.value = uiModels
            } catch (e: Exception) {
                Log.d(TAG, e.getErrorMessage())
            }
        }
    }

}