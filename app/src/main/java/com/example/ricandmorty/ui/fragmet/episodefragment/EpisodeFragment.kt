package com.example.ricandmorty.ui.fragmet.episodefragment

import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.ricandmorty.base.BaseFragment
import com.example.ricandmorty.core.UIState

import com.example.ricandmorty.databinding.FragmentEpisodeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EpisodeFragment : BaseFragment<FragmentEpisodeBinding>(FragmentEpisodeBinding::inflate) {

    private val viewModel: EpisodeViewModel by lazy {
        ViewModelProvider(requireActivity()).get(
            EpisodeViewModel::class.java
        )
    }
    private val adapter: EpisodeAdapter by lazy { EpisodeAdapter() }
    private var page: Int = 1
    override fun setupUI() {
        binding.rvView.adapter = adapter
        fetchData()
    }

    private fun fetchData() {
        viewModel.getEpisode(page)

        binding.refresh.setOnRefreshListener {
            binding.refresh.isRefreshing = false
            page++
            viewModel.getEpisode(page)
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