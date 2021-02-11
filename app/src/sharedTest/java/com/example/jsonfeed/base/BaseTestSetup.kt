package com.example.jsonfeed.base

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.jsonfeed.parser.BaseMockParser.Companion.EXPECTED_NUM_MODELS_ALL_IDS_VALID
import com.example.jsonfeed.parser.BaseMockParser.Companion.EXPECTED_NUM_MODELS_FOR_NO_DATA
import com.example.jsonfeed.parser.BaseMockParser.Companion.EXPECTED_NUM_MODELS_WHEN_TWO_EMPTY
import com.example.jsonfeed.parser.BaseMockParser.Companion.EXPECTED_NUM_MODELS_WHEN_TWO_IDS_ABSENT
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
        verifyListSize(EXPECTED_NUM_MODELS_ALL_IDS_VALID, items)
    }

    protected fun <T> verifyListSizeWhenSomeIdsAbsent(items: List<T>) {
        verifyListSize(EXPECTED_NUM_MODELS_WHEN_TWO_IDS_ABSENT, items)
    }

    protected fun <T> verifyListSizeWhenAllIdsAbsent(items: List<T>) {
        verifyListSize(EXPECTED_NUM_MODELS_FOR_NO_DATA, items)
    }

    protected fun <T> verifyListSizeWhenJsonEmpty(items: List<T>) {
        verifyListSize(EXPECTED_NUM_MODELS_FOR_NO_DATA, items)
    }

    protected fun <T> verifyListSizeWhenSomeItemsEmpty(items: List<T>) {
        verifyListSize(EXPECTED_NUM_MODELS_WHEN_TWO_EMPTY, items)
    }

}