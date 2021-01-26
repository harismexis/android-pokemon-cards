package com.example.jsonfeed.workshared.application

import android.app.Application
import io.reactivex.plugins.RxJavaPlugins

class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        initRxErrorHandler()
    }

    private fun initRxErrorHandler() {
        RxJavaPlugins.setErrorHandler { ex: Throwable ->

        }
    }

}