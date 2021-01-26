package com.example.jsonfeed.workshared.activity

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar

abstract class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
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