package com.example.jsonfeed.base

import androidx.arch.core.executor.testing.InstantTaskExecutorRule

import com.example.jsonfeed.rules.MainCoroutineScopeRule

import kotlinx.coroutines.ExperimentalCoroutinesApi

import org.junit.Assert
import org.junit.Rule

abstract class BaseTestSetup {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    @get:Rule
    val coroutineScope = MainCoroutineScopeRule()

    companion object {
        const val NUM_MODELS_ALL_IDS_VALID = 2
        const val NUM_MODELS_SOME_IDS_MISSING = 1
        const val NUM_MODELS_ALL_IDS_MISSING = 0
    }

    protected fun <T, P> verifyListsHaveSameSize(
        list0: List<T>,
        list1: List<P>
    ) {
        Assert.assertEquals(list0.size, list1.size)
    }

    private fun <T> verifyListSize(
        expectedSize: Int,
        items: List<T>
    ) {
        Assert.assertEquals(expectedSize, items.size)
    }

    protected fun <T> verifyListSizeWhenAllIdsValid(items: List<T>) {
        verifyListSize(NUM_MODELS_ALL_IDS_VALID, items)
    }

    protected fun <T> verifyListSizeWhenSomeIdsAbsent(items: List<T>) {
        verifyListSize(NUM_MODELS_SOME_IDS_MISSING, items)
    }

    protected fun <T> verifyListSizeWhenAllIdsAbsent(items: List<T>) {
        verifyListSize(NUM_MODELS_ALL_IDS_MISSING, items)
    }

}