package com.example.jsonfeed.home.ui

import android.view.View
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.jsonfeed.base.BaseActivity
import com.example.jsonfeed.databinding.ActivityHomeBinding
import com.example.jsonfeed.datamodel.PokemonItem
import com.example.jsonfeed.detail.ui.ItemDetailActivity.Companion.startItemDetailActivity
import com.example.jsonfeed.home.adapter.PokemonAdapter
import com.example.jsonfeed.home.viewholder.PokemonItemVh
import com.example.jsonfeed.home.viewmodel.PokemonViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class HomeActivity : BaseActivity(), PokemonItemVh.PokemonItemClickListener {

    private lateinit var viewModel: PokemonViewModel
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

    override fun observeLiveData() {
       //
    }

    private fun initialiseRecycler() {
        adapter = PokemonAdapter(this)
        binding.homeList.layoutManager = LinearLayoutManager(this)
        binding.homeList.adapter = adapter
    }

    override fun onPokemonItemClick(item: PokemonItem, position: Int) {
        item.id?.let {
            startItemDetailActivity(it)
        }
    }

    override fun initialiseViewModel() {
        viewModel = ViewModelProviders.of(this, viewModelFactory)[PokemonViewModel::class.java]
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

}