package com.example.jsonfeed.di.component

import com.example.jsonfeed.application.InstrumentedMainApplication
import com.example.jsonfeed.di.ActivityBindingsModule
import com.example.jsonfeed.di.module.InstrumentedApplicationModule
import com.example.jsonfeed.mockviewmodel.factory.InstrumentedViewModelModule

import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule

import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        ActivityBindingsModule::class,
        InstrumentedViewModelModule::class,
        InstrumentedApplicationModule::class
    ]
)
interface InstrumentedMainComponent : AndroidInjector<InstrumentedMainApplication> {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance application: InstrumentedMainApplication): InstrumentedMainComponent
    }

}