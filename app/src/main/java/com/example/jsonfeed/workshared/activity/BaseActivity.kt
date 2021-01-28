package com.example.jsonfeed.workshared.activity

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
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        initialiseViewBinding()
        setContentView(getRootView())
        initialiseView()
        initialiseViewModel()
    }

    abstract fun initialiseViewBinding()

    abstract fun getRootView(): View

    abstract fun initialiseViewModel()

    abstract fun getToolbar(): Toolbar?

    open fun initialiseView() {
        setupActionBar()
    }

    open fun setupActionBar() {
        setSupportActionBar(getToolbar())
        supportActionBar?.setDisplayShowTitleEnabled(false)
    }
}