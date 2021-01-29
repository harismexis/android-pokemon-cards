package com.example.jsonfeed.home.viewmodel

import android.util.Log

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

import com.example.jsonfeed.home.repository.FeedRepository
import com.example.jsonfeed.model.FeedItem
import com.example.jsonfeed.extensions.getErrorMessage
import com.example.jsonfeed.localdb.LocalFeedItem
import com.example.jsonfeed.localdb.repository.LocalRepository

import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

import javax.inject.Inject

class HomeVm @Inject constructor(
    var feedRepo: FeedRepository,
    var localRepo: LocalRepository
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

    suspend fun saveItemsLocally_2(items: List<FeedItem>) {
        localRepo.insertFeedItems(convertToLocalItems((items)))
    }

    fun fetchFakeItems() {
        val items = createFakeItems()
        saveItemsLocally(items)
        mModels.value = items
    }

    private fun saveItemsLocally(items: List<FeedItem>) {
        viewModelScope.launch {
            try {
                localRepo.insertFeedItems(convertToLocalItems(items))
            } catch (e: Exception) {
                Log.d(TAG, e.getErrorMessage())
            }
        }
    }

    private fun convertToLocalItems(items: List<FeedItem>): List<LocalFeedItem> {
        val localItems = mutableListOf<LocalFeedItem>()
        var id = 1
        for (item in items) {
            localItems.add(LocalFeedItem(id, item.name, item.metadata))
            id++
        }
        return localItems
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