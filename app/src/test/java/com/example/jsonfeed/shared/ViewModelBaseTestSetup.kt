//package com.example.jsonfeed.shared
//
//import com.example.jsonfeed.base.BaseTestSetup
//import com.example.jsonfeed.localdb.repository.LocalRepository
//
//import io.reactivex.plugins.RxJavaPlugins
//
//import org.mockito.Mock
//import org.mockito.MockitoAnnotations
//
//abstract class ViewModelBaseTestSetup : BaseTestSetup() {
//
//    @Mock
//    protected lateinit var mockLocalRepo:
//
//    protected fun doBeforeTest() {
//        MockitoAnnotations.initMocks(this)
//        initialiseClassUnderTest()
//        setupRxErrorHandler()
//    }
//
//    private fun setupRxErrorHandler() {
//        RxJavaPlugins.setErrorHandler {
//            // Do nothing
//        }
//    }
//
//    abstract fun initialiseClassUnderTest()
//
//}