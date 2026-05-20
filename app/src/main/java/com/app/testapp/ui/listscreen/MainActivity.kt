package com.app.testapp.ui.listscreen

import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.testapp.R
import com.app.testapp.base.BaseActivity
import com.app.testapp.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlin.getValue

@AndroidEntryPoint
class MainActivity : BaseActivity(), MainNavigator {
    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()
    private lateinit var adapter: MainAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        setContentView(binding.root)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        viewModel.navigator = this
        initToolBarTitle("Test title")
        setupRecyclerView()
        fetchList()
        observeViewModel()
    }

    private fun setupRecyclerView() {
        adapter = MainAdapter()
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapter
    }

    private fun observeViewModel() {
        viewModel.repositories.observe(this) { repositories ->
            repositories?.let { adapter.updateList(it) }
        }
    }

    private fun fetchList(){
        viewModel.fetchRepositories()
    }

    override fun onSearchClicked() {

    }
}