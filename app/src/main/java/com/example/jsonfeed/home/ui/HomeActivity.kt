package com.example.jsonfeed.home.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.example.jsonfeed.databinding.ActivityHomeBinding
import com.example.jsonfeed.home.viewmodel.HomeVm
import dagger.android.AndroidInjection
import javax.inject.Inject

class HomeActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var viewModel: HomeVm
    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(icicle: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(icicle)
        initialiseViewBinding()
        setContentView(binding.root)
        initialiseViewModel()
    }

    private fun initialiseViewBinding() {
        binding = ActivityHomeBinding.inflate(layoutInflater)
    }

    private fun initialiseViewModel() {
        viewModel = ViewModelProviders.of(this, viewModelFactory)[HomeVm::class.java]
    }

}