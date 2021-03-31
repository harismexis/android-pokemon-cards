package com.harismexis.pokemon.presentation.home.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.harismexis.pokemon.domain.Item
import com.harismexis.pokemon.framework.Interactors
import com.harismexis.pokemon.framework.extensions.getErrorMessage
import com.harismexis.pokemon.framework.util.functional.Action1
import com.harismexis.pokemon.framework.util.network.ConnectivityMonitor
import kotlinx.coroutines.launch
import javax.inject.Inject

class HomeVm @Inject constructor(
    val interactors: Interactors,
    var connectivity: ConnectivityMonitor,
) : ViewModel() {

    private val TAG = HomeVm::class.qualifiedName

    private val mModels = MutableLiveData<List<Item>>()
    val models: LiveData<List<Item>>
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
                val items = interactors.iRRGetRemoteItems.invoke()
                items?.let {
                    mModels.value = it
                    interactors.iRRStoreItems.invoke(items)
                }
            } catch (e: Exception) {
                Log.d(TAG, e.getErrorMessage())
            }
        }
    }

    private fun fetchLocalFeed() {
        viewModelScope.launch {
            try {
                val items = interactors.iRRGetLocalItems.invoke()
                items?.let {
                    mModels.value = it
                }
            } catch (e: Exception) {
                Log.d(TAG, e.getErrorMessage())
            }
        }
    }

}