package com.example.jsonfeed.ui.detail.ui

import android.content.Context
import android.content.Intent
import android.view.View
import androidx.appcompat.widget.Toolbar
import com.example.jsonfeed.base.BaseActivity
import com.example.jsonfeed.databinding.ActivityItemDetailBinding
import com.example.jsonfeed.databinding.ItemDetailViewBinding
import com.example.jsonfeed.datamodel.remote.PokemonItem
import com.example.jsonfeed.extensions.populateWithGlide
import com.example.jsonfeed.extensions.setTextOrUnknown

class PokemonDetailActivity : BaseActivity() {

    private lateinit var binding: ActivityItemDetailBinding
    private lateinit var detailBinding: ItemDetailViewBinding

    companion object {
        private const val EXTRA_POKEMON_ITEM = "pokemon_item"

        fun Context.startItemDetailActivity(item: PokemonItem) {
            this.startActivity(createIntent(this, item))
        }

        private fun createIntent(
            context: Context,
            item: PokemonItem
        ): Intent {
            return Intent(context, PokemonDetailActivity::class.java)
                .apply { putExtra(EXTRA_POKEMON_ITEM, item) }
        }
    }

    override fun initialise() {
        super.initialise()
        retrievePokemon()
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

    private fun retrievePokemon() {
        val pokemon = intent.getParcelableExtra<PokemonItem>(EXTRA_POKEMON_ITEM)
        pokemon?.let {
            populate(it)
        }
    }

    private fun populate(model: PokemonItem) {
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

    override fun observeLiveData() {}

    override fun initialiseViewModel() {}

    override fun inject() {}

}