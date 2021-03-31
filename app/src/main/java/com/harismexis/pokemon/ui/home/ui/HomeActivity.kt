package com.harismexis.pokemon.ui.home.ui

import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.widget.Toolbar
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.paging.CombinedLoadStates
import androidx.paging.LoadState
import com.harismexis.pokemon.base.BaseActivity
import com.harismexis.pokemon.databinding.ActivityHomeBinding
import com.harismexis.pokemon.extensions.getErrorMessage
import com.harismexis.pokemon.extensions.showErrorToast
import com.harismexis.pokemon.model.PokemonItem
import com.harismexis.pokemon.ui.detail.ui.PokemonDetailActivity.Companion.startItemDetailActivity
import com.harismexis.pokemon.ui.home.adapter.PokemonAdapter
import com.harismexis.pokemon.ui.home.adapter.PokemonLoadStateAdapter
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
        fetchPokemons()
    }

    private fun fetchPokemons() {
        searchJob?.cancel()
        searchJob = lifecycleScope.launch {
            viewModel.getPokemonCardsStream().collectLatest {
                adapter.submitData(it)
            }
        }
    }

    private fun initialiseRecycler() {
        adapter = PokemonAdapter(this)
        binding.homeList.adapter = adapter.withLoadStateHeaderAndFooter(
            header = PokemonLoadStateAdapter { adapter.retry() },
            footer = PokemonLoadStateAdapter { adapter.retry() }
        )
        setupLoadStateListener()
    }

    private fun setupLoadStateListener() {
        adapter.addLoadStateListener { loadState ->
            val isListEmpty = loadState.refresh is LoadState.NotLoading && adapter.itemCount == 0
            showEmptyList(isListEmpty)

            // Only show the list if refresh succeeds.
            binding.homeList.isVisible = loadState.mediator?.refresh is LoadState.NotLoading
            // Show loading spinner during initial load or refresh.
            binding.progressBar.isVisible = loadState.mediator?.refresh is LoadState.Loading
            // Show the retry state if initial load or refresh fails.
            binding.retryButton.isVisible = loadState.mediator?.refresh is LoadState.Error

            showToastOnErrorAppendOrPrepend(loadState)
        }
    }

    private fun showToastOnErrorAppendOrPrepend(loadState: CombinedLoadStates) {
        // Toast on any error, regardless of whether it came from RemoteMediator or PagingSource
        val errorState = loadState.source.append as? LoadState.Error
            ?: loadState.source.prepend as? LoadState.Error
            ?: loadState.append as? LoadState.Error
            ?: loadState.prepend as? LoadState.Error
        errorState?.let {
            this.showErrorToast(it.error.getErrorMessage())
        }
    }

    private fun showEmptyList(show: Boolean) {
        if (show) {
            binding.emptyList.visibility = View.VISIBLE
            binding.homeList.visibility = View.GONE
        } else {
            binding.emptyList.visibility = View.GONE
            binding.homeList.visibility = View.VISIBLE
        }
    }

    override fun onPokemonItemClick(item: PokemonItem, position: Int) {
        startItemDetailActivity(item.id)
    }

    override fun initialiseViewBinding() {
        binding = ActivityHomeBinding.inflate(layoutInflater)
    }

    override fun initialiseView() {
        initialiseRecycler()
        binding.retryButton.setOnClickListener { adapter.retry() }
    }

    override fun getRootView(): View {
        return binding.root
    }

    override fun getToolbar(): Toolbar {
        return binding.homeToolbar
    }

}