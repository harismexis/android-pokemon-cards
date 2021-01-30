package com.example.jsonfeed.detail.viewmodel

import android.util.Log

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

import com.example.jsonfeed.extensions.getErrorMessage
import com.example.jsonfeed.localdb.LocalFeedItem
import com.example.jsonfeed.localdb.repository.LocalRepository

import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

import javax.inject.Inject

class ItemDetailVm @Inject constructor(
    var localRepo: LocalRepository
) : ViewModel() {

    private val tag = ItemDetailVm::class.qualifiedName

    private val mModel = MutableLiveData<LocalFeedItem?>()
    val model: LiveData<LocalFeedItem?>
        get() = mModel

    lateinit var itemId: String

    private var jobGetLocalItem: Job? = null

    fun retrieveItemById() {
        itemId?.let {
            jobGetLocalItem = viewModelScope.launch {
                try {
                    val item = localRepo.getFeedItemById(it)
                    mModel.value = item
                    Log.d(tag, mModel.value?.id.toString())
                } catch (e: Exception) {
                    Log.d(tag, e.getErrorMessage())
                }
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        cancelJobGetLocalItem()
    }

    private fun cancelJobGetLocalItem() {
        jobGetLocalItem?.cancel()
        jobGetLocalItem = null
    }

}