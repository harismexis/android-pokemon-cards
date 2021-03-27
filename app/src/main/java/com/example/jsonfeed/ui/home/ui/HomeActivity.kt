package com.example.jsonfeed.ui.home.ui

import android.view.View
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.jsonfeed.base.BaseActivity
import com.example.jsonfeed.databinding.ActivityHomeBinding
import com.example.jsonfeed.datamodel.remote.PokemonItem
import com.example.jsonfeed.ui.detail.ui.PokemonDetailActivity.Companion.startItemDetailActivity
import com.example.jsonfeed.ui.home.adapter.PokemonAdapter
import com.example.jsonfeed.ui.home.viewholder.PokemonItemVh
import com.example.jsonfeed.ui.home.viewmodel.HomeVm
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class HomeActivity : BaseActivity(), PokemonItemVh.PokemonItemClickListener {

    private lateinit var viewModel: HomeVm
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

    override fun initialiseViewModel() {
        viewModel = ViewModelProviders.of(this, viewModelFactory)[HomeVm::class.java]
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