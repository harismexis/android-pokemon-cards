package com.example.jsonfeed.home.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jsonfeed.extensions.convertToLocalItems

import com.example.jsonfeed.extensions.convertToUiModels
import com.example.jsonfeed.extensions.getErrorMessage
import com.example.jsonfeed.localdb.repository.LocalRepository
import com.example.jsonfeed.repository.FeedRepository
import com.example.jsonfeed.uimodel.UiModel

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

    private val mModels = MutableLiveData<List<UiModel>>()
    val models: LiveData<List<UiModel>>
        get() = mModels

    override fun onCleared() {
        super.onCleared()
        cancelJobFeed()
        cancelJobSave()
    }

    fun fetchJsonFeed() {
        jobFeed = viewModelScope.launch {
            try {
                val response = feedRepo.getJsonFeed()
                val uiModels = response.convertToUiModels()
                mModels.value = uiModels
                val localItems = uiModels.convertToLocalItems()
                localRepo.insertItems(localItems)
            } catch (e: Exception) {
                Log.d(TAG, e.getErrorMessage())
            }
        }
    }

    private fun cancelJobFeed() {
        jobFeed?.cancel()
        jobFeed = null
    }

    private fun cancelJobSave() {
        jobSave?.cancel()
        jobSave = null
    }

}