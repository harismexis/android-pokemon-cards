package com.example.jsonfeed.base

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.jsonfeed.rules.MainCoroutineScopeRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
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
}