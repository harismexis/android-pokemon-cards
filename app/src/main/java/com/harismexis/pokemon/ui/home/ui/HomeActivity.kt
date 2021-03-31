package com.harismexis.pokemon.ui.home.ui

import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.harismexis.pokemon.base.BaseActivity
import com.harismexis.pokemon.databinding.ActivityHomeBinding
import com.harismexis.pokemon.datamodel.remote.PokemonItem
import com.harismexis.pokemon.ui.detail.ui.PokemonDetailActivity.Companion.startItemDetailActivity
import com.harismexis.pokemon.ui.home.adapter.PokemonAdapter
import com.harismexis.pokemon.ui.home.viewholder.PokemonItemVh
import com.harismexis.pokemon.ui.home.viewmodel.HomeVm
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class HomeActivity : BaseActivity(), PokemonItemVh.PokemonItemClickListener {

    private val viewModel: HomeVm by viewModels { viewModelFactory }
    private lateinit var binding: ActivityHomeBinding
    private lateinit var adapter: PokemonAdapter
    private var searchJob: Job? = null

    override fun initialise() {
        super.initialise()
        fetchPokemon()
    }

    private fun fetchPokemon() {
        searchJob?.cancel()
        searchJob = lifecycleScope.launch {
            viewModel.getPokemonCardsStream().collectLatest {
                adapter.submitData(it)
            }
        }
    }

    private fun initialiseRecycler() {
        adapter = PokemonAdapter(this)
        binding.homeList.layoutManager = LinearLayoutManager(this)
        binding.homeList.adapter = adapter
    }

    override fun onPokemonItemClick(item: PokemonItem, position: Int) {
        item.id?.let {
            startItemDetailActivity(item)
        }
    }

    override fun initialiseViewBinding() {
        binding = ActivityHomeBinding.inflate(layoutInflater)
    }

    override fun initialiseView() {
        initialiseRecycler()
    }

    override fun getRootView(): View {
        return binding.root
    }

    override fun getToolbar(): Toolbar {
        return binding.homeToolbar
    }

    override fun observeLiveData() {}
}