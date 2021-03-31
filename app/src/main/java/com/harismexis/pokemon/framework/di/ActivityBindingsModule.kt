package com.harismexis.pokemon.framework.di

import com.harismexis.pokemon.framework.base.BaseActivity
import com.harismexis.pokemon.presentation.detail.ui.ItemDetailActivity
import com.harismexis.pokemon.presentation.home.ui.HomeActivity

import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBindingsModule {

    @ContributesAndroidInjector
    abstract fun baseActivity(): BaseActivity

    @ContributesAndroidInjector
    abstract fun homeActivity(): HomeActivity

    @ContributesAndroidInjector
    abstract fun itemDetailActivity(): ItemDetailActivity

}
