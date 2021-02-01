package com.example.jsonfeed.home.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule

import com.example.jsonfeed.R
import com.example.jsonfeed.extensions.toLocalItems
import com.example.jsonfeed.extensions.toUiModels
import com.example.jsonfeed.home.viewmodel.HomeVm
import com.example.jsonfeed.mockprovider.provideMockFeedAllIdsMissing
import com.example.jsonfeed.mockprovider.provideMockFeedValid
import com.example.jsonfeed.mocks.MockProvider
import io.mockk.every

import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val testRule: ActivityTestRule<HomeActivity> =
        ActivityTestRule(HomeActivity::class.java, false, false)

    lateinit var mockViewModel: HomeVm

    @Before
    fun setup() {
        mockViewModel = MockProvider.provideMockViewModel()
        // justRun { mockViewModel.bind() }
        // justRun { mockViewModel.unbind() }
    }

    @Test
    fun viewModelUpdatesLiveData_UiIsUpdatedWithCreditScoreData() {
        // given
        val mockFeedValid = provideMockFeedAllIdsMissing()
        val expectedLocalItems = mockFeedValid.toLocalItems()
        val expectedUiModels = expectedLocalItems.toUiModels()

        every { mockViewModel.models } returns MockProvider.models
//        runBlocking {
//            Mockito.`when`(mockViewModel.models).thenReturn(MockProvider.models)
//        }

        testRule.launchActivity(null)
        testRule.activity.runOnUiThread {
            // when
            MockProvider.mModels.value = expectedUiModels
        }

        // then
        onView(withId(R.id.home_list)).check(matches(isDisplayed()))
        onView(withId(R.id.home_list)).check(matches(hasChildCount(0)))
//        assertEquals(MAX_SCORE, scoreProgressBar.max)
    }

}
