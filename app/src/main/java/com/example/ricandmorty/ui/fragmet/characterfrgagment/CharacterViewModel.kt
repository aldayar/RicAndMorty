package com.example.ricandmorty.ui.fragmet.characterfrgagment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.ricandmorty.core.UIState
import com.example.ricandmorty.data.model.MainRes
import com.example.ricandmorty.data.remote.CharacterRepository

class CharacterViewModel:ViewModel() {
    private val repository = CharacterRepository()

    var liveData:LiveData <UIState<MainRes>> =MutableLiveData()
    var liveDataById:LiveData <UIState<MainRes>> =MutableLiveData()

    fun getCharacters(page: Int){
        liveData = repository.getCharacters(page )
    }
    fun getCharacterById(id:Int){
        liveDataById = repository.getCharacterById(id)
    }
}