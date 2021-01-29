package com.example.jsonfeed.detail.viewmodel

import android.util.Log

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

import com.example.jsonfeed.extensions.getErrorMessage
import com.example.jsonfeed.localdb.LocalFeedItem
import com.example.jsonfeed.localdb.repository.LocalRepository

import kotlinx.coroutines.launch

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
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