package com.example.jsonfeed.detail.ui

import android.content.Context
import android.content.Intent
import android.view.View
import android.widget.Toast

import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.ViewModelProviders

import com.example.jsonfeed.databinding.ActivityItemDetailBinding
import com.example.jsonfeed.databinding.ItemDetailViewBinding
import com.example.jsonfeed.detail.viewmodel.ItemDetailVm
import com.example.jsonfeed.base.BaseActivity
import com.example.jsonfeed.localdb.LocalFeedItem

class ItemDetailActivity : BaseActivity() {

    private lateinit var binding: ActivityItemDetailBinding
    private lateinit var detailBinding: ItemDetailViewBinding
    private lateinit var viewModel: ItemDetailVm

    private lateinit var selectedItemId: String

    companion object {

        private const val EXTRA_ITEM_ID = "item_id"

        fun Context.startItemDetailActivity(value: String) {
            this.startActivity(createIntent(this, value))
        }

        private fun createIntent(
            context: Context,
            value: String
        ): Intent {
            return Intent(context, ItemDetailActivity::class.java).apply {
                value?.let {
                    putExtra(EXTRA_ITEM_ID, value)
                }
            }
        }
    }

    override fun initialise() {
        super.initialise()
        retrieveIntentExtras()
        updateViewModel()
        retrieveItem()
    }

    override fun observeLiveData() {
        viewModel.model.observe(this, {
            updateUI(it)
        })
    }

    override fun initialiseViewModel() {
        viewModel = ViewModelProviders.of(this, viewModelFactory)[ItemDetailVm::class.java]
    }

    override fun initialiseViewBinding() {
        binding = ActivityItemDetailBinding.inflate(layoutInflater)
        detailBinding = binding.itemDetailContainer
    }

    override fun getRootView(): View {
        return binding.root
    }

    override fun getToolbar(): Toolbar {
        return binding.itemDetailToolbar
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    private fun retrieveIntentExtras() {
        selectedItemId = intent.getStringExtra(EXTRA_ITEM_ID)!!
    }

    private fun updateViewModel() {
        viewModel.itemId = selectedItemId
    }

    private fun retrieveItem() {
        viewModel.retrieveItemById()
    }

    private fun updateUI(model: LocalFeedItem?) {
        if (model != null) {
            detailBinding.txtTitle.text = model.name
            detailBinding.txtMeta.text = model.supertype
        } else {
            Toast.makeText(this, "Null Model!", Toast.LENGTH_LONG).show()
        }
    }

}