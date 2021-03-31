package com.harismexis.pokemon.presentation.home.ui

import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import com.harismexis.pokemon.domain.Item
import com.harismexis.pokemon.framework.base.BaseActivity
import com.harismexis.pokemon.presentation.detail.ui.ItemDetailActivity.Companion.startItemDetailActivity
import com.harismexis.pokemon.presentation.home.adapter.HomeAdapter
import com.harismexis.pokemon.databinding.ActivityHomeBinding
import com.harismexis.pokemon.presentation.home.viewholder.FeedItemVh
import com.harismexis.pokemon.presentation.home.viewmodel.HomeVm

class HomeActivity : BaseActivity(), FeedItemVh.FeedItemClickListener {

    private val viewModel: HomeVm by viewModels { viewModelFactory }
    private lateinit var binding: ActivityHomeBinding
    private lateinit var adapter: HomeAdapter
    private var uiModels: MutableList<Item> = mutableListOf()

    override fun initialise() {
        super.initialise()
        setupSwipeToRefresh()
        viewModel.bind()
    }

    override fun initialiseViewBinding() {
        binding = ActivityHomeBinding.inflate(layoutInflater)
    }

    override fun initialiseView() {
        super.initialiseView()
        initialiseRecycler()
    }

    override fun getRootView(): View {
        return binding.root
    }

    override fun onFeedItemClick(item: Item, position: Int) {
        startItemDetailActivity(item.id)
    }

    override fun getToolbar(): Toolbar {
        return binding.homeToolbar
    }

    override fun observeLiveData() {
        viewModel.models.observe(this, {
            populate(it)
        })
    }

    private fun populate(models: List<Item>) {
        binding.homeSwipeRefresh.isRefreshing = false
        binding.loadingProgressBar.visibility = View.GONE
        binding.homeList.visibility = View.VISIBLE
        uiModels.clear()
        uiModels.addAll(models)
        adapter.notifyDataSetChanged()
    }

    private fun initialiseRecycler() {
        adapter = HomeAdapter(uiModels, this)
        adapter.setHasStableIds(true)
        binding.homeList.layoutManager = LinearLayoutManager(this)
        binding.homeList.adapter = adapter
    }

    private fun setupSwipeToRefresh() {
        binding.homeSwipeRefresh.setOnRefreshListener {
            binding.homeSwipeRefresh.isRefreshing = true
            viewModel.refresh { canRefresh ->
                if (!canRefresh) {
                    binding.homeSwipeRefresh.isRefreshing = false
                }
            }
        }
    }

}