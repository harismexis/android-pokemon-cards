package com.example.jsonfeed.home

import com.example.jsonfeed.extensions.toLocalItems
import com.example.jsonfeed.extensions.toUiModels
import com.example.jsonfeed.testutils.mockprovider.provideMockFeedValid
import com.example.jsonfeed.testutils.mockprovider.provideMockLocalItemsValid
import com.nhaarman.mockitokotlin2.verify
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mockito
import org.mockito.Mockito.`when`

@RunWith(JUnit4::class)
class HomeVmTest : HomeVmTestSetup() {

    @Before
    fun setup() {
        doBeforeTest()
    }

    @Test
    fun internetIsOnAndFeedIsValid_remoteCallIsDoneAndDataIsStoredLocallyAndLiveDataIsUpdated() {
        // given
        val mockFeedValid = provideMockFeedValid()
        val expectedUiModels = mockFeedValid.toUiModels()
        val expectedLocalItems = expectedUiModels.toLocalItems()
        `when`(mockConnectivity.isOnline()).thenReturn(true)
        runBlocking {
            `when`(mockFeedRepo.getJsonFeed()).thenReturn(mockFeedValid)
        }

        // when
        homeVm.bind()

        // then
        runBlocking {
            verify(mockFeedRepo, Mockito.times(1)).getJsonFeed()
            verify(observer).onChanged(expectedUiModels)
            verify(mockLocalRepo, Mockito.times(1)).insertItems(expectedLocalItems)
        }
    }

    @Test
    fun internetIsOffAndLocalStorageHasItems_localItemsAreFetchedAndLiveDataIsUpdated() {
        // given
        val mockLocalItems = provideMockLocalItemsValid()
        val expectedUiModels = mockLocalItems.toUiModels()
        `when`(mockConnectivity.isOnline()).thenReturn(false)
        runBlocking {
            `when`(mockLocalRepo.getAllItems()).thenReturn(mockLocalItems)
        }

        // when
        homeVm.bind()

        // then
        runBlocking {
            verify(mockFeedRepo, Mockito.times(0)).getJsonFeed()
            verify(mockLocalRepo, Mockito.times(1)).getAllItems()
            verify(observer).onChanged(expectedUiModels)
        }
    }

}