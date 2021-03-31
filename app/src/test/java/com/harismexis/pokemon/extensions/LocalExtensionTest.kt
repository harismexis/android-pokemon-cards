package com.harismexis.pokemon.extensions

import com.harismexis.pokemon.base.BaseTestSetup
import com.harismexis.pokemon.mockprovider.getMockLocalItemsFromFeedAllIdsValid
import com.harismexis.pokemon.utils.verifyUiModelAgainstLocalItem
import org.junit.Test

class LocalExtensionTest : BaseTestSetup() {

    @Test
    fun localItemsAreConvertedToUiModels_uiModelsAreCorrect() {
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