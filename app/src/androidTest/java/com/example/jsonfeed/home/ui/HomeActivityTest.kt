package com.example.jsonfeed.home.ui

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.contrib.RecyclerViewActions.scrollToPosition
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.intent.Intents.intended
import androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.example.jsonfeed.R
import com.example.jsonfeed.base.BaseTestSetup
import com.example.jsonfeed.domain.LocalFeedItem
import com.example.jsonfeed.framework.extensions.toLocalItems


import com.example.jsonfeed.mockprovider.*
import com.example.jsonfeed.mockproviders.MockHomeVmProvider
import com.example.jsonfeed.presentation.detail.ui.ItemDetailActivity
import com.example.jsonfeed.presentation.home.ui.HomeActivity
import com.example.jsonfeed.presentation.home.viewmodel.HomeVm

import com.example.jsonfeed.utils.RecyclerViewItemCountAssertion
import com.example.jsonfeed.utils.RecyclerViewMatcher
import io.mockk.every
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class HomeActivityTest : BaseTestSetup() {

    @get:Rule
    val testRule: ActivityTestRule<HomeActivity> =
        ActivityTestRule(
            HomeActivity::class.java,
            false, false
        )

    lateinit var mockHomeVm: HomeVm
    lateinit var mockUiModels: List<LocalFeedItem>

    @Before
    fun doBeforeTest() {
        Intents.init()
        //mockUiModels = getMockFeedAllIdsValid().toLocalItems().toUiModels()
        mockHomeVm = MockHomeVmProvider.provideMockHomeVm()
        every { mockHomeVm.bind() } returns Unit
    }

    @Test
    fun liveDataUpdatedFromFeedWithAllItemsValid_homeListHasCorrectNumberOfItems() {
        // given
        every { mockHomeVm.models } returns MockHomeVmProvider.models
        launchActivityAndMockLiveData()

        // then
        onView(withId(R.id.home_list)).check(matches(isDisplayed()))
        onView(withId(R.id.home_list)).check(RecyclerViewItemCountAssertion(mockUiModels.size))
        onView(withId(R.id.home_list)).check(
            RecyclerViewItemCountAssertion(EXPECTED_NUM_MODELS_ALL_FEED_IDS_VALID)
        )
        verifyViewHoldersShowCorrectData()
    }

    @Test
    fun liveDataUpdatedFromFeedWithSomeIdsAbsent_homeListHasCorrectNumberOfItems() {
        // given
        //mockUiModels = getMockFeedSomeIdsAbsent().toLocalItems().toUiModels()
        every { mockHomeVm.models } returns MockHomeVmProvider.models
        launchActivityAndMockLiveData()

        // then
        onView(withId(R.id.home_list)).check(matches(isDisplayed()))
        onView(withId(R.id.home_list)).check(RecyclerViewItemCountAssertion(mockUiModels.size))
        onView(withId(R.id.home_list)).check(
            RecyclerViewItemCountAssertion(EXPECTED_NUM_MODELS_SOME_FEED_IDS_ABSENT)
        )
        verifyViewHoldersShowCorrectData()
    }

    @Test
    fun liveDataUpdatedFromFeedWithAllIdsAbsent_homeListHasNoItems() {
        // given
        //mockUiModels = getMockFeedAllIdsAbsent().toLocalItems().toUiModels()
        every { mockHomeVm.models } returns MockHomeVmProvider.models
        launchActivityAndMockLiveData()

        // then
        onView(withId(R.id.home_list)).check(matches(isDisplayed()))
        onView(withId(R.id.home_list)).check(RecyclerViewItemCountAssertion(mockUiModels.size))
        onView(withId(R.id.home_list)).check(
            RecyclerViewItemCountAssertion(EXPECTED_NUM_MODELS_ALL_FEED_IDS_ABSENT)
        )
    }

    @Test
    fun liveDataUpdatedFromFeedWithSomeJsonItemsEmpty_homeListHasCorrectNumberOfItems() {
        // given
        //mockUiModels = getMockFeedSomeItemsEmpty().toLocalItems().toUiModels()
        every { mockHomeVm.models } returns MockHomeVmProvider.models
        launchActivityAndMockLiveData()

        // then
        onView(withId(R.id.home_list)).check(matches(isDisplayed()))
        onView(withId(R.id.home_list)).check(RecyclerViewItemCountAssertion(mockUiModels.size))
        onView(withId(R.id.home_list)).check(
            RecyclerViewItemCountAssertion(EXPECTED_NUM_MODELS_SOME_FEED_ITEMS_EMPTY)
        )
        verifyViewHoldersShowCorrectData()
    }

    @Test
    fun liveDataUpdatedFromEmptyJson_homeListHasNoItems() {
        // given
        //mockUiModels = getMockFeedEmptyJson().toLocalItems().toUiModels()
        every { mockHomeVm.models } returns MockHomeVmProvider.models
        launchActivityAndMockLiveData()

        // then
        onView(withId(R.id.home_list)).check(matches(isDisplayed()))
        onView(withId(R.id.home_list)).check(RecyclerViewItemCountAssertion(mockUiModels.size))
        onView(withId(R.id.home_list)).check(
            RecyclerViewItemCountAssertion(EXPECTED_NUM_MODELS_EMPTY_JSON)
        )
    }

    @Test
    fun clickOnHomeListItem_opensItemDetailActivity() {
        // given
        every { mockHomeVm.models } returns MockHomeVmProvider.models
        launchActivityAndMockLiveData()

        // when
        clickRecyclerAt(0)

        // then
        intended(hasComponent(ItemDetailActivity::class.java.name))
    }

    private fun clickRecyclerAt(position: Int) {
        onView(withId(R.id.home_list)).perform(
            actionOnItemAtPosition<RecyclerView.ViewHolder>(
                position,
                click()
            )
        )
    }

    private fun verifyViewHoldersShowCorrectData() {
        mockUiModels.forEachIndexed { index, uiModel ->
            // scroll to item to make sure it's visible
            onView(withId(R.id.home_list)).perform(scrollToPosition<RecyclerView.ViewHolder>(index))
            // check name text
            onView(withRecyclerView(R.id.home_list).atPositionOnView(index, R.id.txt_name))
                .check(matches(withText(uiModel.name)))
            // check meta text
            onView(withRecyclerView(R.id.home_list).atPositionOnView(index, R.id.txt_meta))
                .check(matches(withText(uiModel.supertype)))
        }
    }

    private fun withRecyclerView(recyclerViewId: Int): RecyclerViewMatcher {
        return RecyclerViewMatcher(recyclerViewId)
    }

    private fun launchActivityAndMockLiveData() {
        testRule.launchActivity(null)
        testRule.activity.runOnUiThread {
            MockHomeVmProvider.mModels.value = mockUiModels
        }
    }

    @After
    fun doAfterTest() {
        Intents.release()
    }

}
