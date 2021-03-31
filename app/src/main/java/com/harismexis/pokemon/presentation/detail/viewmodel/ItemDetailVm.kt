package com.harismexis.pokemon.presentation.detail.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.harismexis.pokemon.framework.Interactors
import com.harismexis.pokemon.framework.extensions.getErrorMessage
import com.harismexis.pokemon.domain.Item
import kotlinx.coroutines.launch
import javax.inject.Inject

class ItemDetailVm @Inject constructor(
    val interactors: Interactors
) : ViewModel() {

    private val tag = ItemDetailVm::class.qualifiedName

    private val mModel = MutableLiveData<Item>()
    val model: LiveData<Item>
        get() = mModel

    fun retrieveItemById(itemId: String) {
        viewModelScope.launch {
            try {
                val item = interactors.iRRGetLocalItem(itemId)
                item?.let {
                    mModel.value = it
                }
            } catch (e: Exception) {
                Log.d(tag, e.getErrorMessage())
            }
        }
    }

}
