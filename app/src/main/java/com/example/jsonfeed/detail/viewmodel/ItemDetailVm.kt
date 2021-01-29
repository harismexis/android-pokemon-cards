package com.example.jsonfeed.detail.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jsonfeed.model.FeedItem
import com.example.jsonfeed.workshared.extensions.getErrorMessage
import com.example.jsonfeed.workshared.localdb.LocalFeedItem
import com.example.jsonfeed.workshared.localdb.repository.LocalRepository
import kotlinx.coroutines.launch

import javax.inject.Inject

class ItemDetailVm @Inject constructor(
    var localRepo: LocalRepository
) : ViewModel() {

    private val tag = ItemDetailVm::class.qualifiedName

    private val mModel = MutableLiveData<LocalFeedItem>()
    val model: LiveData<LocalFeedItem>
        get() = mModel

    var itemId: Int? = null

    fun retrieveItemById() {
        itemId?.let {
            viewModelScope.launch {
                try {
                    mModel.value = localRepo.getFeedItemById(it)
                } catch (e: Exception) {
                    Log.d(tag, e.getErrorMessage())
                }
            }
        }

    }


}