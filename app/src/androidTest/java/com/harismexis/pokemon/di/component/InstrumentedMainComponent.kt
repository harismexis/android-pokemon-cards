package com.harismexis.pokemon.di.component

import com.harismexis.pokemon.application.InstrumentedMainApplication
import com.harismexis.pokemon.di.ActivityBindingsModule
import com.harismexis.pokemon.di.module.InstrumentedApplicationModule
import com.harismexis.pokemon.factory.InstrumentedViewModelModule

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