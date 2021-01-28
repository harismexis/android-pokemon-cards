package com.example.jsonfeed.detail.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View

import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders

import com.example.jsonfeed.databinding.ActivityItemDetailBinding
import com.example.jsonfeed.detail.viewmodel.ItemDetailVm
import com.example.jsonfeed.workshared.activity.BaseActivity

import dagger.android.AndroidInjection

import javax.inject.Inject

class ItemDetailActivity : BaseActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var binding: ActivityItemDetailBinding
    private lateinit var viewModel: ItemDetailVm

    companion object {

        private const val EXTRA_ITEM_ID = "item_id"

        fun Context.startItemDetailActivity(value: String?) {
            this.startActivity(createIntent(this, value))
        }

        private fun createIntent(
            context: Context,
            value: String?
        ): Intent {
            return Intent(context, ItemDetailActivity::class.java).apply {
                value?.let {
                    if (value.isNotEmpty()) {
                        putExtra(EXTRA_ITEM_ID, value)
                    }
                }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun initialiseViewModel() {
        viewModel = ViewModelProviders.of(this, viewModelFactory)[ItemDetailVm::class.java]
    }

    override fun initialiseViewBinding() {
        binding = ActivityItemDetailBinding.inflate(layoutInflater)
    }

    override fun initialiseView() {

    }

    override fun getRootView(): View {
        return binding.root
    }

    override fun getToolbar(): Toolbar? {
        return null
    }

}