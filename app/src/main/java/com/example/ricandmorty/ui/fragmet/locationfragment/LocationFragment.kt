package com.example.ricandmorty.ui.fragmet.locationfragment

import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.ricandmorty.base.BaseFragment
import com.example.ricandmorty.core.UIState
import com.example.ricandmorty.databinding.FragmentLocationBinding
import com.example.ricandmorty.ui.fragmet.episodefragment.EpisodeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LocationFragment : BaseFragment<FragmentLocationBinding>(FragmentLocationBinding::inflate) {

    private var page: Int = 1
    private val adapter: LocationAdapter by lazy { LocationAdapter() }
    private val viewModel: LocationViewModel by lazy {
        ViewModelProvider(requireActivity()).get(
            LocationViewModel::class.java
        )
    }
    override fun setupUI() {
        binding.rvView.adapter = adapter
        fetchData()
    }

    private fun fetchData() {
        viewModel.getLocation(page)

        binding.refresh.setOnRefreshListener {
            binding.refresh.isRefreshing = false
            page++
            viewModel.getLocation(page)
        }
        viewModel.liveData.observe(viewLifecycleOwner){state->
            when (state){
                is UIState.Loading->{
                    binding.progressBar.visibility = View.VISIBLE
                }
                is UIState.Success->{
                    binding.progressBar.visibility = View.GONE
                    state.data?.results.let {episodes ->
                        adapter.submitList(episodes)
                    }
                }
                is UIState.Error->{
                    Toast.makeText(requireContext(), "Error 303, please try again", Toast.LENGTH_SHORT).show()
                    Log.e(state.msg.toString(), "ololo", )
                }
            }
        }
    }
    }


