package com.example.jsonfeed.home.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jsonfeed.home.repository.FeedRepo

import com.example.jsonfeed.model.FeedItem
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject

class HomeVm @Inject constructor(
    var feedRepo: FeedRepo
//    var connectivity: ConnectivityMonitor,
) : ViewModel() {

    private val TAG = HomeVm::class.qualifiedName
    private var jobFeed: Job? = null

    private val mModels = MutableLiveData<List<FeedItem>>()
    val models: LiveData<List<FeedItem>>
        get() = mModels

    override fun onCleared() {
        super.onCleared()
        jobFeed?.cancel()
        jobFeed = null
    }

    fun fetchJsonFeed() {
        jobFeed = viewModelScope.launch {
            try {
//                val response = marsRepo.getCuriosityLatestMRP()
//                mModels.value = response.toUiModel().mrpItems
            } catch (e: Exception) {
//                Log.d(TAG, e.getErrorMessage())
            }
        }
    }

    fun fetchFakeItems() {
        mModels.value = createFakeItems()
    }

    private fun createFakeItems(): List<FeedItem> {
        val items = arrayListOf<FeedItem>()
        items.add(FeedItem("Knife", "Tool to slash & stab"))
        items.add(FeedItem("Stick", "Tool for breaking bones"))
        items.add(FeedItem("Nunchuck", "Tool for self defense"))
        items.add(FeedItem("Double Stick", "Tool to destroy my enemies"))
        return items
    }

}