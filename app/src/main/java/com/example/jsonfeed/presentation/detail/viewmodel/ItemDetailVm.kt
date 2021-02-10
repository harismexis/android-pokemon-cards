package com.example.jsonfeed.presentation.detail.viewmodel

import android.util.Log

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

import com.example.jsonfeed.domain.LocalItem
import com.example.jsonfeed.framework.extensions.getErrorMessage
import com.example.jsonfeed.framework.Interactors

import kotlinx.coroutines.launch
import javax.inject.Inject

class ItemDetailVm @Inject constructor(
    val interactors: Interactors
) : ViewModel() {

    private val tag = ItemDetailVm::class.qualifiedName

    private val mModel = MutableLiveData<LocalItem>()
    val model: LiveData<LocalItem>
        get() = mModel

    fun retrieveItemById(itemId: String) {
        viewModelScope.launch {
            try {
                val item = interactors.getLocalFeedItem(itemId)
                item?.let {
                    mModel.value = it
                }
            } catch (e: Exception) {
                Log.d(tag, e.getErrorMessage())
            }
        }
    }

}
