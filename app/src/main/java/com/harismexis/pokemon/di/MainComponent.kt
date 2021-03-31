package com.harismexis.pokemon.di

import com.harismexis.pokemon.application.MainApplication
import com.harismexis.pokemon.viewmodel.factory.ViewModelModule
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
        ViewModelModule::class,
        ApplicationModule::class,
        PokemonApiModule::class]
)
interface MainComponent : AndroidInjector<MainApplication> {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance application: MainApplication): MainComponent
    }

}