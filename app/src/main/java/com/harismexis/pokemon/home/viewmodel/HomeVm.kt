package com.harismexis.pokemon.home.viewmodel

import android.util.Log

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

import com.harismexis.pokemon.localdb.repository.LocalRepository
import com.harismexis.pokemon.repository.FeedRepository
import com.harismexis.pokemon.uimodel.UiModel
import com.harismexis.pokemon.util.network.ConnectivityMonitor
import com.harismexis.pokemon.extensions.getErrorMessage
import com.harismexis.pokemon.extensions.toLocalItems
import com.harismexis.pokemon.extensions.toUiModels
import com.harismexis.pokemon.util.functional.Action1

import kotlinx.coroutines.launch

import javax.inject.Inject

class HomeVm @Inject constructor(
    var feedRepo: FeedRepository,
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
                val response = feedRepo.getJsonFeed()
                val localItems = response.toLocalItems()
                val uiModels = localItems.toUiModels()
                mModels.value = uiModels
                localRepo.insertItems(localItems)
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