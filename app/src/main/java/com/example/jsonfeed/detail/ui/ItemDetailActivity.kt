package com.example.jsonfeed.detail.ui

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.net.Uri
import android.view.View
import androidx.annotation.Nullable

import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition

import com.example.jsonfeed.databinding.ActivityItemDetailBinding
import com.example.jsonfeed.databinding.ItemDetailViewBinding
import com.example.jsonfeed.detail.viewmodel.ItemDetailVm
import com.example.jsonfeed.base.BaseActivity
import com.example.jsonfeed.extensions.setTextOrUnknown
import com.example.jsonfeed.localdb.LocalItem
import com.example.jsonfeed.uimodel.UiModel

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
            return Intent(context, ItemDetailActivity::class.java)
                .apply { putExtra(EXTRA_ITEM_ID, value) }
        }
    }

    override fun initialise() {
        super.initialise()
        retrieveIntentExtras()
        retrieveSelectedItem()
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

    private fun retrieveSelectedItem() {
        viewModel.itemId = selectedItemId
        viewModel.retrieveItemById()
    }

    private fun updateUI(model: UiModel) {
        model.imageUrlHiRes?.let {
            populateImage(it)
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

    private fun populateImage(imgUrl: String) {
        showLoadingView(true)
        Glide.with(this)
            .asBitmap()
            .load(Uri.parse(imgUrl))
            .diskCacheStrategy(DiskCacheStrategy.NONE)
            .into(object : CustomTarget<Bitmap?>() {
                override fun onResourceReady(
                    resource: Bitmap,
                    transition: Transition<in Bitmap?>?
                ) {
                    showLoadingView(false)
                    detailBinding.img.setImageBitmap(resource)
                }

                override fun onLoadCleared(@Nullable placeholder: Drawable?) {}
            })
    }

    private fun showLoadingView(show: Boolean) {
        if (show) {
            detailBinding.img.visibility = View.GONE
            detailBinding.progressContainer.visibility = View.VISIBLE
        } else {
            detailBinding.img.visibility = View.VISIBLE
            detailBinding.progressContainer.visibility = View.GONE
        }
    }

}