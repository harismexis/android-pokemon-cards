package com.harismexis.pokemon.shared

import com.harismexis.pokemon.base.BaseTestSetup
import com.harismexis.pokemon.localdb.repository.LocalRepository

import io.reactivex.plugins.RxJavaPlugins

import org.mockito.Mock
import org.mockito.MockitoAnnotations

abstract class ViewModelBaseTestSetup : BaseTestSetup() {

    @Mock
    protected lateinit var mockLocalRepo: LocalRepository

    protected fun doBeforeTest() {
        MockitoAnnotations.initMocks(this)
        initialiseClassUnderTest()
        setupRxErrorHandler()
    }

    private fun setupRxErrorHandler() {
        RxJavaPlugins.setErrorHandler {
            // Do nothing
        }
    }

    abstract fun initialiseClassUnderTest()

}