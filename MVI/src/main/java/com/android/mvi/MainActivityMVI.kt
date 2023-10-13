package com.android.mvi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.mvi.data.api.ApiHelper
import com.android.mvi.data.api.ApiHelperImpl
import com.android.mvi.data.api.RetrofitBuilder
import com.android.mvi.data.model.Users
import com.android.mvi.databinding.ActivityMainMviBinding
import com.android.mvi.uis.adapters.MainAdapter
import com.android.mvi.uis.intents.DataIntent
import com.android.mvi.uis.viewmodel.DataViewModel
import com.android.mvi.uis.viewstates.DataState
import com.android.mvi.utils.ViewModelFactory
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MainActivityMVI : AppCompatActivity() {
    private lateinit var dataViewModel: DataViewModel
    private var adapter = MainAdapter(arrayListOf())
    lateinit var binding: ActivityMainMviBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainMviBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupUI()
        setupViewModel()
        observeViewModel()
        lifecycleScope.launch {
            dataViewModel.dataIntent.send(DataIntent.FetchData)
        }
    }


    private fun observeViewModel() {
        lifecycleScope.launch {
            dataViewModel.dataState.collect {
                when (it) {
                    is DataState.Inactive -> {
                        Log.d("Inactive", "Inactive State")
                    }

                    is DataState.Loading -> {
                        binding.progressBar.visibility = android.view.View.VISIBLE
                        binding.buttonShowUsers.visibility = android.view.View.GONE
                    }

                    is DataState.ResponseData -> {
                        binding.progressBar.visibility = android.view.View.GONE
                        binding.buttonShowUsers.visibility = android.view.View.GONE
                        /*
                        * Adding all the success data to the recycler view
                        * */
                        renderList(it.data.data)
                    }

                    is DataState.Error -> {
                        binding.progressBar.visibility = android.view.View.GONE
                        binding.buttonShowUsers.visibility = android.view.View.VISIBLE
                        Log.d("Error", "Error State")
                    }
                }
            }
        }
    }

    private fun renderList(users: List<Users>) {
       binding.recyclerView.visibility = android.view.View.VISIBLE
            users.let { listOfUsers -> listOfUsers.let { adapter.addUsers(it) } }
            adapter.notifyDataSetChanged()
    }

    private fun setupViewModel() {
       dataViewModel = ViewModelProvider(this,
           ViewModelFactory(ApiHelperImpl(RetrofitBuilder.apiService))
       )[DataViewModel::class.java]
    }

    private fun setupUI() {
       binding.recyclerView.layoutManager = LinearLayoutManager(this)

        /**
         * This is the scope function run which is used to initialize the recycler view
         * the return value of the run function is the recycler view itself
         * run does the same as with but it is implemented as an extension function.
         * So like let, you can call it on the context object using dot notation.
         * run is useful when your lambda both initializes objects and computes the return value.
         */
        binding.recyclerView.run {
            addItemDecoration(DividerItemDecoration(context, (layoutManager as LinearLayoutManager).orientation))
        }
        binding.recyclerView.adapter = adapter
    }

}