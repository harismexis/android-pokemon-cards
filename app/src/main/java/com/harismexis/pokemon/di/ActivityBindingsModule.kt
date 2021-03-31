package com.harismexis.pokemon.di

import com.harismexis.pokemon.base.BaseActivity
import com.harismexis.pokemon.ui.detail.ui.PokemonDetailActivity
import com.harismexis.pokemon.ui.home.ui.HomeActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBindingsModule {

    @ContributesAndroidInjector
    abstract fun baseActivity(): BaseActivity

    @ContributesAndroidInjector
    abstract fun homeActivity(): HomeActivity

    @ContributesAndroidInjector
    abstract fun itemDetailActivity(): PokemonDetailActivity

}
