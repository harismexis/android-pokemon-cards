package com.example.jsonfeed.di

import com.example.jsonfeed.base.BaseActivity
import com.example.jsonfeed.ui.home.ui.HomeActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBindingsModule {

    @ContributesAndroidInjector
    abstract fun baseActivity(): BaseActivity

    @ContributesAndroidInjector
    abstract fun homeActivity(): HomeActivity

}
