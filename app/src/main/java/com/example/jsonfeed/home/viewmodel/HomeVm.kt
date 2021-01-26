package com.example.jsonfeed.home.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

import com.example.jsonfeed.model.FeedItem
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class HomeVm(
//    var marsRepo: MRPRepo,
//    var connectivity: ConnectivityMonitor,
) : ViewModel() {

    private val TAG = HomeVm::class.qualifiedName
    private var jobGetMrp: Job? = null

    private val mModels = MutableLiveData<List<FeedItem>>()
    val models: LiveData<List<FeedItem>>
        get() = mModels

    override fun onCleared() {
        super.onCleared()
        jobGetMrp?.cancel()
        jobGetMrp = null
    }

    fun fetchJsonFeed() {
        jobGetMrp = viewModelScope.launch {
            try {
//                val response = marsRepo.getCuriosityLatestMRP()
//                mModels.value = response.toUiModel().mrpItems
            } catch (e: Exception) {
//                Log.d(TAG, e.getErrorMessage())
            }
        }
    }

}