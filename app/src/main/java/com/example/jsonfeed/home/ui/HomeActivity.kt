package com.example.jsonfeed.home.ui

import android.os.Bundle
import android.view.View

import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager

import com.example.jsonfeed.databinding.ActivityHomeBinding
import com.example.jsonfeed.detail.ui.ItemDetailActivity.Companion.startItemDetailActivity
import com.example.jsonfeed.home.adapter.HomeAdapter
import com.example.jsonfeed.home.viewholder.FeedItemVh
import com.example.jsonfeed.home.viewmodel.HomeVm
import com.example.jsonfeed.model.FeedItem
import com.example.jsonfeed.base.BaseActivity

class HomeActivity : BaseActivity(), FeedItemVh.FeedItemClickListener {

    private lateinit var viewModel: HomeVm
    private lateinit var binding: ActivityHomeBinding
    private lateinit var adapter: HomeAdapter
    private var feedItems: MutableList<FeedItem> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.fetchFakeItems()
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

    override fun onFeedItemClick(item: FeedItem, position: Int) {
        val itemId = position + 1
        startItemDetailActivity(itemId)
    }

    override fun getToolbar(): Toolbar? {
        return null
    }

    override fun observeLiveData() {
        viewModel.models.observe(this, {
            updateUI(it)
        })
    }

    private fun updateUI(models: List<FeedItem>) {
        feedItems.clear()
        feedItems.addAll(models)
        adapter.notifyDataSetChanged()
    }

    private fun initialiseRecycler() {
        adapter = HomeAdapter(feedItems, this)
        binding.homeList.layoutManager = LinearLayoutManager(this)
        binding.homeList.adapter = adapter
    }

}