package com.harismexis.pokemon.shared

import com.harismexis.pokemon.base.BaseTestSetup
import com.harismexis.pokemon.unittestutil.UnitTestMockParser
import org.mockito.MockitoAnnotations

abstract class UnitTestSetup : BaseTestSetup() {

    protected val mockParser = UnitTestMockParser()

    open fun initialise() {
        MockitoAnnotations.initMocks(this)
        initialiseClassUnderTest()
    }

    abstract fun initialiseClassUnderTest()

}