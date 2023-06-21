package com.example.ricandmorty.ui.fragmet.characterfrgagment

import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.ricandmorty.core.UIState
import com.example.ricandmorty.data.BaseFragment
import com.example.ricandmorty.data.model.Characters
import com.example.ricandmorty.data.model.MainRes
import com.example.ricandmorty.databinding.FragmentCharactrerDataBinding

class CharactrerDataFragment :
    BaseFragment<FragmentCharactrerDataBinding>(FragmentCharactrerDataBinding::inflate) {

    private val viewModel by lazy { ViewModelProvider(requireActivity())[CharacterViewModel::class.java] }

    override fun setupUI() {
        if (arguments != null) {
            val id = arguments?.getInt("model")!!
            viewModel.getCharacterById(id)
            viewModel.liveDataById.observe(viewLifecycleOwner) { model ->
                when (model) {
                    is UIState.Success -> {
                        showCharacter(model.data as Characters)
                    }
                    is UIState.Loading -> {
                        binding.progressBar.visibility = View.VISIBLE
                    }
                    is UIState.Error -> {
                        Log.e("setupUI: ", model.msg.toString())
                        Toast.makeText(
                            requireContext(),
                            "Can't load data,please try again",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }
        }
    }

    private fun showCharacter(character: Characters) {
        binding.tvCharacterName.text = character.name
        binding.tvCharacterGender.text = character.gender
        binding.tvIsAlive.text = character.name
        Glide.with(binding.root).load(character.image).centerCrop().into(binding.imgCharacter)
    }
}