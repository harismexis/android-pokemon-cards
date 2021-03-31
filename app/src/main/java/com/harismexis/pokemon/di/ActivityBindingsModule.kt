package com.harismexis.pokemon.di

import com.harismexis.pokemon.home.ui.HomeActivity
import com.harismexis.pokemon.base.BaseActivity
import com.harismexis.pokemon.detail.ui.ItemDetailActivity
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
