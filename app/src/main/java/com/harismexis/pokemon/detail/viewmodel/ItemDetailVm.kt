package com.harismexis.pokemon.detail.viewmodel

import android.util.Log

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

import com.harismexis.pokemon.extensions.getErrorMessage
import com.harismexis.pokemon.extensions.toUiModel
import com.harismexis.pokemon.localdb.repository.LocalRepository
import com.harismexis.pokemon.uimodel.UiModel

import kotlinx.coroutines.launch

import javax.inject.Inject

class ItemDetailVm @Inject constructor(
    var localRepo: LocalRepository
) : ViewModel() {

    private val tag = ItemDetailVm::class.qualifiedName

    private val mModel = MutableLiveData<UiModel>()
    val model: LiveData<UiModel>
        get() = mModel

    fun retrieveItemById(itemId: String) {
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