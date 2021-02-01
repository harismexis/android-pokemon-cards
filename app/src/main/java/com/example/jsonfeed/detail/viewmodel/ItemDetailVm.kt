package com.example.jsonfeed.detail.viewmodel

import android.util.Log

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

import com.example.jsonfeed.extensions.getErrorMessage
import com.example.jsonfeed.extensions.toUiModel
import com.example.jsonfeed.localdb.repository.LocalRepository
import com.example.jsonfeed.uimodel.UiModel

import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

import javax.inject.Inject

class ItemDetailVm @Inject constructor(
    var localRepo: LocalRepository
) : ViewModel() {

    private val tag = ItemDetailVm::class.qualifiedName

    lateinit var itemId: String
    private val mModel = MutableLiveData<UiModel>()
    val model: LiveData<UiModel>
        get() = mModel

    fun retrieveItemById() {
        viewModelScope.launch {
            try {
                val item = localRepo.getItemById(itemId)
                item?.let {
                    mModel.value = it.toUiModel()
                }
            } catch (e: Exception) {
                Log.d(tag, e.getErrorMessage())
            }
        }
    }

}