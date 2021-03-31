package com.harismexis.pokemon.presentation.detail.ui

import android.content.Context
import android.content.Intent
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.widget.Toolbar
import com.harismexis.pokemon.databinding.ActivityItemDetailBinding
import com.harismexis.pokemon.databinding.ItemDetailViewBinding
import com.harismexis.pokemon.domain.Item
import com.harismexis.pokemon.framework.base.BaseActivity
import com.harismexis.pokemon.framework.extensions.populateWithGlide
import com.harismexis.pokemon.framework.extensions.setTextOrUnknown
import com.harismexis.pokemon.presentation.detail.viewmodel.ItemDetailVm

class ItemDetailActivity : BaseActivity() {

    private val viewModel: ItemDetailVm by viewModels { viewModelFactory }
    private lateinit var binding: ActivityItemDetailBinding
    private lateinit var detailBinding: ItemDetailViewBinding

    companion object {
        private const val EXTRA_ITEM_ID = "item_id"

        fun Context.startItemDetailActivity(value: String) {
            this.startActivity(createIntent(this, value))
        }

        private fun createIntent(
            context: Context,
            value: String
        ): Intent {
            return Intent(context, ItemDetailActivity::class.java)
                .apply { putExtra(EXTRA_ITEM_ID, value) }
        }
    }

    override fun initialise() {
        super.initialise()
        retrieveSelectedItem()
    }

    override fun observeLiveData() {
        viewModel.model.observe(this, {
            populate(it)
        })
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

    private fun retrieveSelectedItem() {
        val selectedItemId = intent.getStringExtra(EXTRA_ITEM_ID)
        selectedItemId?.let {
            viewModel.retrieveItemById(it)
        }
    }

    private fun populate(model: Item) {
        model.imageUrlHiRes?.let {
            this.populateWithGlide(detailBinding.img, it)
        }
        detailBinding.txtName.setTextOrUnknown(model.name)
        detailBinding.txtSupertype.setTextOrUnknown(model.supertype)
        detailBinding.txtSubtype.setTextOrUnknown(model.subtype)
        detailBinding.txtArtist.setTextOrUnknown(model.artist)
        detailBinding.txtRarity.setTextOrUnknown(model.rarity)
        detailBinding.txtSeries.setTextOrUnknown(model.series)
        detailBinding.txtSet.setTextOrUnknown(model.set)
        detailBinding.txtSetCode.setTextOrUnknown(model.setCode)
    }

}