package com.example.ricandmorty.ui.fragmet.characterfrgagment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.ricandmorty.core.UIState
import com.example.ricandmorty.data.model.Characters
import com.example.ricandmorty.data.model.MainRes
import com.example.ricandmorty.data.remote.CharacterRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CharacterViewModel @Inject constructor(private val repository: CharacterRepository):ViewModel() {

    var liveData:LiveData <UIState<MainRes<Characters>>> =MutableLiveData()
    var liveDataById:LiveData <UIState<MainRes<Characters>>> =MutableLiveData()

    fun getCharacters(page: Int){
        liveData = repository.getCharacters(page )
    }
    fun getCharacterById(id:Int){
        liveDataById = repository.getCharacterById(id)
    }
}