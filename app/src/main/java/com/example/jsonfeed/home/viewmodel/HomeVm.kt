package com.example.jsonfeed.home.viewmodel

import android.util.Log

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

import com.example.jsonfeed.datamodel.FeedItem
import com.example.jsonfeed.repository.FeedRepository
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
    private var jobSave: Job? = null

    private val mModels = MutableLiveData<List<FeedItem>>()
    val models: LiveData<List<FeedItem>>
        get() = mModels

    override fun onCleared() {
        super.onCleared()
        cancelJobFeed()
        cancelJobSave()
    }

    private fun cancelJobFeed() {
        jobFeed?.cancel()
        jobFeed = null
    }

    private fun cancelJobSave() {
        jobSave?.cancel()
        jobSave = null
    }

    fun fetchJsonFeed() {
        jobFeed = viewModelScope.launch {
            try {
                val response = feedRepo.getJsonFeed()
                response?.let { res ->
                    res.cards?.let {
                        mModels.value = it
                        saveItemsLocally(it)
                    }
                }
            } catch (e: Exception) {
                Log.d(TAG, e.getErrorMessage())
            }
        }
    }

    suspend fun saveItemsLocally(items: List<FeedItem>) {
        localRepo.insertFeedItems(convertToLocalItems((items)))
    }

    private fun convertToLocalItems(items: List<FeedItem>): List<LocalFeedItem> {
        val localItems = mutableListOf<LocalFeedItem>()
        var id = 1
        for (item in items) {
            item.id?.let {
                val localItem = convertToLocalItem(item)
                localItem?.let {
                    localItems.add(it)
                }
                id++
            }
        }
        return localItems
    }

    private fun convertToLocalItem(item: FeedItem): LocalFeedItem? {
        item.id?.let {
            return LocalFeedItem(
                it,
                item.name,
                item.imageUrl,
                item.imageUrlHiRes,
                item.supertype,
                item.subtype,
                item.evolvesFrom
            )
        }
        return null
    }

}