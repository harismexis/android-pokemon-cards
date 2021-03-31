package com.harismexis.pokemon.ui.home.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.harismexis.pokemon.extensions.getErrorMessage
import com.harismexis.pokemon.model.PokemonItem
import com.harismexis.pokemon.repository.PokemonRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

class ItemDetailVm @Inject constructor(
    var pokemonRepository: PokemonRepository
) : ViewModel() {

    private val tag = ItemDetailVm::class.qualifiedName

    private val mModel = MutableLiveData<PokemonItem>()
    val model: LiveData<PokemonItem>
        get() = mModel

    fun retrieveItemById(itemId: String) {
        viewModelScope.launch {
            try {
                val item = pokemonRepository.getItemById(itemId)
                item?.let {
                    mModel.value = it
                }
            } catch (e: Exception) {
                Log.d(tag, e.getErrorMessage())
            }
        }
    }

}