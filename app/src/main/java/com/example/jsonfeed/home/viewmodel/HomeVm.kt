package com.example.jsonfeed.home.viewmodel

import android.util.Log

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

import com.example.jsonfeed.extensions.*
import com.example.jsonfeed.localdb.repository.LocalRepository
import com.example.jsonfeed.repository.FeedRepository
import com.example.jsonfeed.uimodel.UiModel
import com.example.jsonfeed.util.network.ConnectivityMonitor

import kotlinx.coroutines.Job
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

    private fun fetchRemoteFeed() {
        viewModelScope.launch {
            try {
                val response = feedRepo.getJsonFeed()
                val uiModels = response.toUiModels()
                mModels.value = uiModels
                val localItems = uiModels.toLocalItems()
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