package com.example.jsonfeed.detail

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.rule.ActivityTestRule

import com.example.jsonfeed.R
import com.example.jsonfeed.base.BaseTestSetup
import com.example.jsonfeed.detail.ui.ItemDetailActivity
import com.example.jsonfeed.detail.viewmodel.ItemDetailVm
import com.example.jsonfeed.extensions.toLocalItems
import com.example.jsonfeed.extensions.toUiModels
import com.example.jsonfeed.mockprovider.getMockFeedAllIdsValid
import com.example.jsonfeed.mockproviders.MockHomeVmProvider
import com.example.jsonfeed.mockproviders.MockItemDetailVmProvider
import com.example.jsonfeed.uimodel.UiModel

import io.mockk.every

import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ItemDetailActivityTest : BaseTestSetup() {

    @get:Rule
    val testRule: ActivityTestRule<ItemDetailActivity> =
        ActivityTestRule(
            ItemDetailActivity::class.java,
            false, false
        )

    lateinit var mockItemDetailVm: ItemDetailVm
    lateinit var mockUiModel: UiModel

    @Before
    fun setup() {
        mockItemDetailVm = MockItemDetailVmProvider.provideMockItemDetailVm()
        every { mockItemDetailVm.retrieveItemById(any()) } returns Unit
        mockUiModel = getMockFeedAllIdsValid().toLocalItems().toUiModels()[0]
    }

    @Test
    fun liveDataChanges_uiIsUpdatedWithCorrectData() {
        // given
        every { mockItemDetailVm.model } returns MockItemDetailVmProvider.model
        launchActivityAndMockLiveData()

        // then
        onView(withId(R.id.img)).check(matches(isDisplayed()))
        onView(withId(R.id.txt_name)).check(matches(withText(mockUiModel.name)))
        onView(withId(R.id.txt_supertype_label)).check(matches(withText(getString(R.string.label_supertype))))
        onView(withId(R.id.txt_supertype)).check(matches(withText(mockUiModel.supertype)))
        onView(withId(R.id.txt_subtype_label)).check(matches(withText(getString(R.string.label_subtype))))
        onView(withId(R.id.txt_subtype)).check(matches(withText(mockUiModel.subtype)))
        onView(withId(R.id.txt_artist_label)).check(matches(withText(getString(R.string.label_artist))))
        onView(withId(R.id.txt_artist)).check(matches(withText(mockUiModel.artist)))
        onView(withId(R.id.txt_rarity_label)).check(matches(withText(getString(R.string.label_rarity))))
        onView(withId(R.id.txt_rarity)).check(matches(withText(mockUiModel.rarity)))
        onView(withId(R.id.txt_series_label)).check(matches(withText(getString(R.string.label_series))))
        onView(withId(R.id.txt_series)).check(matches(withText(mockUiModel.series)))
        onView(withId(R.id.txt_set_label)).check(matches(withText(getString(R.string.label_set))))
        onView(withId(R.id.txt_set)).check(matches(withText(mockUiModel.set)))
        onView(withId(R.id.txt_set_code_label)).check(matches(withText(getString(R.string.label_set_code))))
        onView(withId(R.id.txt_set_code)).check(matches(withText(mockUiModel.setCode)))
    }

    private fun launchActivityAndMockLiveData() {
        testRule.launchActivity(null)
        testRule.activity.runOnUiThread {
            MockItemDetailVmProvider.mModel.value = mockUiModel
        }
    }

    private fun getString(id: Int): String {
        val targetContext = InstrumentationRegistry.getInstrumentation().targetContext
        return targetContext.resources.getString(id)
    }

}
