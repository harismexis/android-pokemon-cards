package com.example.jsonfeed.home.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule

import com.example.jsonfeed.R
import com.example.jsonfeed.detail.ui.ItemDetailActivity
import com.example.jsonfeed.detail.viewmodel.ItemDetailVm
import com.example.jsonfeed.extensions.toLocalItems
import com.example.jsonfeed.extensions.toUiModels
import com.example.jsonfeed.mockprovider.provideMockFeedAllIdsMissing
import com.example.jsonfeed.mockprovider.provideMockFeedValid
import com.example.jsonfeed.mocks.MockProvider
import com.example.jsonfeed.mocks.MockProvider2
import io.mockk.every

import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ItemDetailActivityTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val testRule: ActivityTestRule<ItemDetailActivity> =
        ActivityTestRule(ItemDetailActivity::class.java, false, false)

    lateinit var mockViewModel: ItemDetailVm

    @Before
    fun setup() {
        mockViewModel = MockProvider2.provideMockViewModel()
    }

    @Test
    fun viewModelUpdatesLiveData_UiIsUpdatedWithCreditScoreData() {
        // given
        val mockFeedValid = provideMockFeedValid()
        val expectedLocalItems = mockFeedValid.toLocalItems()
        val expectedUiModel = expectedLocalItems.toUiModels()[0]

        every { mockViewModel.model } returns MockProvider2.model

        testRule.launchActivity(null)
        testRule.activity.runOnUiThread {
            // when
            MockProvider2.mModel.value = expectedUiModel
        }

        // then
        onView(withId(R.id.txt_name)).check(matches(isDisplayed()))
    }

}
