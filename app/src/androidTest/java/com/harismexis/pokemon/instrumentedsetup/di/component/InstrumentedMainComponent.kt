package com.harismexis.pokemon.instrumentedsetup.di.component

import com.harismexis.pokemon.instrumentedsetup.di.module.InstrumentedApplicationModule
import com.harismexis.pokemon.instrumentedsetup.di.module.InstrumentedInteractorModule
import com.harismexis.pokemon.instrumentedsetup.factory.InstrumentedViewModelModule
import com.harismexis.pokemon.framework.di.ActivityBindingsModule
import com.harismexis.pokemon.instrumentedsetup.application.InstrumentedMainApplication
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
        InstrumentedApplicationModule::class,
        InstrumentedInteractorModule::class
    ]
)
interface InstrumentedMainComponent : AndroidInjector<InstrumentedMainApplication> {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance application: InstrumentedMainApplication)
                : InstrumentedMainComponent
    }

}