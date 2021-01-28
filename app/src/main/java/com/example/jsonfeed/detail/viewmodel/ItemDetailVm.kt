package com.example.jsonfeed.detail.viewmodel

import androidx.lifecycle.ViewModel
import com.example.jsonfeed.workshared.localdb.repository.LocalRepository

import javax.inject.Inject

class ItemDetailVm @Inject constructor(
    var localRepo: LocalRepository
) : ViewModel() {

    private val TAG = ItemDetailVm::class.qualifiedName

}