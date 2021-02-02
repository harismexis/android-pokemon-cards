package com.example.jsonfeed.extensions

import com.example.jsonfeed.base.BaseTestSetup
import com.example.jsonfeed.mockprovider.getMockLocalItemsFromFeedAllIdsValid
import com.example.jsonfeed.utils.verifyUiModelAgainstLocalItem

import org.junit.Test

class LocalExtensionTest : BaseTestSetup() {

    @Test
    fun localItemsFromFeedWithAllIdsValid_conversionToUiModelsIsCorrect() {
        // given
        val localItems = getMockLocalItemsFromFeedAllIdsValid()

        // when
        val uiModels = localItems.toUiModels()

        // then
        verifyListsHaveSameSize(localItems, uiModels)
        verifyListSizeWhenAllIdsValid(localItems)
        verifyListSizeWhenAllIdsValid(uiModels)

        localItems.forEachIndexed { index, localItem ->
            val uiModel = uiModels[index]
            verifyUiModelAgainstLocalItem(localItem, uiModel)
        }
    }

}