package com.example.ricandmorty.ui.fragmet.characterfrgagment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.ricandmorty.R
import com.example.ricandmorty.core.UIState
import com.example.ricandmorty.databinding.FragmentCharacterBinding
import com.example.ricandmorty.ui.fragmet.CharacterAdapter
import com.example.ricandmorty.ui.fragmet.CharacterDiffCallback
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CharacterFragment : Fragment(), CharacterDiffCallback.OnClickListener {
    private var page: Int = 1
    private lateinit var binding: FragmentCharacterBinding
    private val viewModel by lazy { ViewModelProvider(requireActivity()).get(CharacterViewModel::class.java) }
    private val adapter by lazy { CharacterAdapter(this) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCharacterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rvView.adapter = adapter
        fetchData()
    }

    private fun fetchData() {
        viewModel.getCharacters(page)

        binding.refresh.setOnRefreshListener {
            binding.refresh.isRefreshing = false
            page++
            viewModel.getCharacters(page)
        }
        viewModel.liveData.observe(viewLifecycleOwner) { state ->
            when (state) {
                is UIState.Success -> {
                    state.data?.results?.let { characters ->
                        adapter.submitList(characters)
                    }
                }
                is UIState.Loading -> {
                    Toast.makeText(requireContext(), "Loading...", Toast.LENGTH_SHORT).show()
                }
                is UIState.Error -> {
                    Toast.makeText(requireContext(), "Error occurred", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    override fun onClick(model: Int) {
        val bundle = Bundle()
        bundle.putInt("model", model)
        findNavController().navigate(R.id.charactrerDataFragment, bundle)

    }
}
