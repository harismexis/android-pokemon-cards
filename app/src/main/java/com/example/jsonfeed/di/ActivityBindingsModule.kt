package com.example.jsonfeed.di

import com.example.jsonfeed.detail.ui.ItemDetailActivity
import com.example.jsonfeed.home.ui.HomeActivity
import com.example.jsonfeed.base.BaseActivity

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
