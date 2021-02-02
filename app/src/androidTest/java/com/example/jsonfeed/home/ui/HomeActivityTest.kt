package com.example.jsonfeed.home.ui

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.intent.Intents.intended
import androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule

import com.example.jsonfeed.R
import com.example.jsonfeed.base.BaseTestSetup
import com.example.jsonfeed.detail.ui.ItemDetailActivity
import com.example.jsonfeed.extensions.toLocalItems
import com.example.jsonfeed.extensions.toUiModels
import com.example.jsonfeed.home.viewmodel.HomeVm
import com.example.jsonfeed.mockprovider.getMockFeedAllIdsValid
import com.example.jsonfeed.mockproviders.MockHomeVmProvider
import com.example.jsonfeed.uimodel.UiModel
import com.example.jsonfeed.utils.RecyclerViewItemCountAssertion

import io.mockk.every

import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class HomeActivityTest: BaseTestSetup() {

    @get:Rule
    val testRule: ActivityTestRule<HomeActivity> =
        ActivityTestRule(
            HomeActivity::class.java,
            false, false
        )

    lateinit var mockHomeVm: HomeVm
    lateinit var expectedUiModels: List<UiModel>

    @Before
    fun doBeforeTest() {
        Intents.init()
        mockHomeVm = MockHomeVmProvider.provideMockHomeVm()
        every { mockHomeVm.bind() } returns Unit
        every { mockHomeVm.models } returns MockHomeVmProvider.models
        expectedUiModels = getMockFeedAllIdsValid().toLocalItems().toUiModels()
    }

    @Test
    fun viewModelsGivesMockUiModels_homeListHasCorrectNumberOfItems() {
        // given
        launchHomeActivityAndMockLiveData()

        // then
        onView(withId(R.id.home_list)).check(matches(isDisplayed()))
        onView(withId(R.id.home_list)).check(RecyclerViewItemCountAssertion(expectedUiModels.size))
    }

    @Test
    fun clickOnHomeListItem_opensItemDetailActivity() {
        // given
        launchHomeActivityAndMockLiveData()

        // when
        onView(withId(R.id.home_list)).perform(
            actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                click()
            )
        )

        // then
        intended(hasComponent(ItemDetailActivity::class.java.name))
    }

    @After
    fun doAfterTest() {
        Intents.release()
    }

    private fun launchHomeActivityAndMockLiveData() {
        testRule.launchActivity(null)
        testRule.activity.runOnUiThread {
            MockHomeVmProvider.mModels.value = expectedUiModels
        }
    }

}
