package com.example.jsonfeed.base

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.ViewModelProvider
import dagger.android.AndroidInjection
import javax.inject.Inject

abstract class BaseActivity : AppCompatActivity() {

    @Inject
    protected lateinit var viewModelFactory: ViewModelProvider.Factory

    override fun onCreate(savedInstanceState: Bundle?) {
        inject()
        super.onCreate(savedInstanceState)
        initialise()
    }

    open fun inject() {
        AndroidInjection.inject(this)
    }

    open fun initialise() {
        initialiseViewBinding()
        setContentView(getRootView())
        initialiseView()
        observeLiveData()
    }

    abstract fun initialiseViewBinding()

    abstract fun getRootView(): View

    abstract fun getToolbar(): Toolbar?

    abstract fun observeLiveData()

    open fun initialiseView() {
        setupActionBar()
    }

    open fun setupActionBar() {
        setSupportActionBar(getToolbar())
        supportActionBar?.setDisplayShowTitleEnabled(false)
    }

}