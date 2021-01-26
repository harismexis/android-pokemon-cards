package com.example.jsonfeed.home

import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

import org.mockito.MockitoAnnotations

@RunWith(JUnit4::class)
class HomeViewModelTest : HomeViewModelTestSetup() {

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
//        setupMocks()
//        setupClassUnderTest()
//        setupRxErrorHandler()
    }

    @Test
    fun feedResponseIsValid_networkCallDoneAndDataUpdated() {

    }

}