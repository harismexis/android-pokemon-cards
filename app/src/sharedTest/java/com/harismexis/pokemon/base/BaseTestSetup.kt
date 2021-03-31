package com.harismexis.pokemon.base

import androidx.arch.core.executor.testing.InstantTaskExecutorRule

import com.harismexis.pokemon.rules.MainCoroutineScopeRule

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
        const val EXPECTED_NUM_MODELS_ALL_FEED_IDS_VALID = 5
        const val EXPECTED_NUM_MODELS_SOME_FEED_IDS_ABSENT = 3
        const val EXPECTED_NUM_MODELS_ALL_FEED_IDS_ABSENT = 0
        const val EXPECTED_NUM_MODELS_SOME_FEED_ITEMS_EMPTY = 2
        const val EXPECTED_NUM_MODELS_EMPTY_JSON = 0
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
        verifyListSize(EXPECTED_NUM_MODELS_ALL_FEED_IDS_VALID, items)
    }

    protected fun <T> verifyListSizeWhenSomeIdsAbsent(items: List<T>) {
        verifyListSize(EXPECTED_NUM_MODELS_SOME_FEED_IDS_ABSENT, items)
    }

    protected fun <T> verifyListSizeWhenAllIdsAbsent(items: List<T>) {
        verifyListSize(EXPECTED_NUM_MODELS_ALL_FEED_IDS_ABSENT, items)
    }

    protected fun <T> verifyListSizeWhenSomeItemsEmpty(items: List<T>) {
        verifyListSize(EXPECTED_NUM_MODELS_SOME_FEED_ITEMS_EMPTY, items)
    }

    protected fun <T> verifyListSizeWhenJsonIsEmpty(items: List<T>) {
        verifyListSize(EXPECTED_NUM_MODELS_EMPTY_JSON, items)
    }

}