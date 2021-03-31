package com.harismexis.pokemon.tests

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
import com.harismexis.pokemon.R
import com.harismexis.pokemon.domain.Item
import com.harismexis.pokemon.instrumentedsetup.base.InstrumentedTestSetup
import com.harismexis.pokemon.instrumentedsetup.instrumentedutil.RecyclerViewItemCountAssertion
import com.harismexis.pokemon.instrumentedsetup.instrumentedutil.RecyclerViewMatcher
import com.harismexis.pokemon.instrumentedsetup.mockvm.MockHomeVmProvider
import com.harismexis.pokemon.parser.BaseMockParser.Companion.EXPECTED_NUM_MODELS_ALL_IDS_VALID
import com.harismexis.pokemon.parser.BaseMockParser.Companion.EXPECTED_NUM_MODELS_FOR_NO_DATA
import com.harismexis.pokemon.parser.BaseMockParser.Companion.EXPECTED_NUM_MODELS_WHEN_TWO_EMPTY
import com.harismexis.pokemon.parser.BaseMockParser.Companion.EXPECTED_NUM_MODELS_WHEN_TWO_IDS_ABSENT
import com.harismexis.pokemon.presentation.detail.ui.ItemDetailActivity
import com.harismexis.pokemon.presentation.home.ui.HomeActivity
import com.harismexis.pokemon.presentation.home.viewmodel.HomeVm
import io.mockk.every
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class HomeActivityTest : InstrumentedTestSetup() {

    @get:Rule
    val testRule: ActivityTestRule<HomeActivity> =
        ActivityTestRule(
            HomeActivity::class.java,
            false, false
        )

    private lateinit var mockHomeVm: HomeVm
    private lateinit var mockItems: List<Item>

    @Before
    fun doBeforeTest() {
        Intents.init()
        mockItems = mockParser.getMockItemsFromFeedWithAllItemsValid()
        mockHomeVm = MockHomeVmProvider.provideMockHomeVm()
        every { mockHomeVm.bind() } returns Unit
    }

    @Test
    fun remoteFeedHasAllItemsValid_then_homeListHasExpectedItems() {
        // given
        every { mockHomeVm.models } returns MockHomeVmProvider.models
        launchActivityAndMockLiveData()

        // then
        onView(withId(R.id.home_list)).check(matches(isDisplayed()))
        onView(withId(R.id.home_list)).check(RecyclerViewItemCountAssertion(mockItems.size))
        onView(withId(R.id.home_list)).check(
            RecyclerViewItemCountAssertion(EXPECTED_NUM_MODELS_ALL_IDS_VALID)
        )
        verifyRecyclerViewShowsExpectedData()
    }

    @Test
    fun remoteFeedHasSomeIdsAbsent_homeListHasExpectedNumberOfItems() {
        // given
        mockItems = mockParser.getMockItemsFromFeedWithSomeIdsAbsent()
        every { mockHomeVm.models } returns MockHomeVmProvider.models
        launchActivityAndMockLiveData()

        // then
        onView(withId(R.id.home_list)).check(matches(isDisplayed()))
        onView(withId(R.id.home_list)).check(RecyclerViewItemCountAssertion(mockItems.size))
        onView(withId(R.id.home_list)).check(
            RecyclerViewItemCountAssertion(EXPECTED_NUM_MODELS_WHEN_TWO_IDS_ABSENT)
        )
        verifyRecyclerViewShowsExpectedData()
    }

    @Test
    fun remoteFeedHasAllIdsAbsent_homeListHasNoItems() {
        // given
        mockItems = mockParser.getMockItemsFromFeedWithAllIdsAbsent()
        every { mockHomeVm.models } returns MockHomeVmProvider.models
        launchActivityAndMockLiveData()

        // then
        onView(withId(R.id.home_list)).check(matches(isDisplayed()))
        onView(withId(R.id.home_list)).check(RecyclerViewItemCountAssertion(mockItems.size))
        onView(withId(R.id.home_list)).check(
            RecyclerViewItemCountAssertion(EXPECTED_NUM_MODELS_FOR_NO_DATA)
        )
    }

    @Test
    fun remoteFeedHasSomeJsonItemsEmpty_homeListHasExpectedNumberOfItems() {
        // given
        mockItems = mockParser.getMockItemsFromFeedWithSomeItemsEmpty()
        every { mockHomeVm.models } returns MockHomeVmProvider.models
        launchActivityAndMockLiveData()

        // then
        onView(withId(R.id.home_list)).check(matches(isDisplayed()))
        onView(withId(R.id.home_list)).check(RecyclerViewItemCountAssertion(mockItems.size))
        onView(withId(R.id.home_list)).check(
            RecyclerViewItemCountAssertion(EXPECTED_NUM_MODELS_WHEN_TWO_EMPTY)
        )
        verifyRecyclerViewShowsExpectedData()
    }

    @Test
    fun remoteFeedHasEmptyJson_homeListHasNoItems() {
        // given
        mockItems = mockParser.getMockItemsFromFeedWithEmptyJson()
        every { mockHomeVm.models } returns MockHomeVmProvider.models
        launchActivityAndMockLiveData()

        // then
        onView(withId(R.id.home_list)).check(matches(isDisplayed()))
        onView(withId(R.id.home_list)).check(RecyclerViewItemCountAssertion(mockItems.size))
        onView(withId(R.id.home_list)).check(
            RecyclerViewItemCountAssertion(EXPECTED_NUM_MODELS_FOR_NO_DATA)
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

    private fun verifyRecyclerViewShowsExpectedData() {
        mockItems.forEachIndexed { index, uiModel ->
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
            MockHomeVmProvider.mModels.value = mockItems
        }
    }

    @After
    fun doAfterTest() {
        Intents.release()
    }

}
