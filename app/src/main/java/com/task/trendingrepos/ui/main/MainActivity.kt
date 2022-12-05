package com.task.trendingrepos.ui.main

import TrendingRepoAdapter
import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import android.view.Menu
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.task.trendingrepos.R
import com.task.trendingrepos.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val mainViewModel: MainViewModel by viewModels()

    private val trendingRepoAdapter: TrendingRepoAdapter by lazy { TrendingRepoAdapter() }

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUpRepoViews()
        observeData()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.search, menu)
        val searchViewItem = menu?.findItem(R.id.action_search)
        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        val searchView: SearchView? = searchViewItem?.actionView as SearchView?
        searchView?.queryHint = "Search"
        searchView?.setSearchableInfo(searchManager.getSearchableInfo(componentName))

        val queryTextListener: SearchView.OnQueryTextListener =
            object : SearchView.OnQueryTextListener {
                override fun onQueryTextChange(newText: String): Boolean {
                    mainViewModel.searchRepo(newText)
                    return true
                }

                override fun onQueryTextSubmit(query: String): Boolean {
                    mainViewModel.searchRepo(query)
                    return true
                }
            }
        searchView?.setOnQueryTextListener(queryTextListener)
        return true
    }

    private fun setUpRepoViews() {
        binding.rvTrendingRepo.run {
            layoutManager = LinearLayoutManager(this@MainActivity)
            trendingRepoAdapter.onItemClick = object : TrendingRepoAdapter.OnItemClick {
                override fun onItemClick(position: Int, isSelected: Boolean) {
                    mainViewModel.onItemClicked(position, isSelected)
                    trendingRepoAdapter.notifyItemChanged(position)
                }
            }
            adapter = trendingRepoAdapter
        }
    }

    private fun observeData() {
        mainViewModel.run {
            fetchTrendingRepos()
            trendingRepoData.observe(this@MainActivity) {
                trendingRepoAdapter.updateRepoList(it)
            }
            isError.observe(this@MainActivity) {
                binding.rvTrendingRepo.isVisible = it.not()
                binding.tvError.isVisible = it
            }
        }
    }
}