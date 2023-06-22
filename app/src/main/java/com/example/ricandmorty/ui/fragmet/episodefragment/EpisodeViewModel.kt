package com.example.ricandmorty.ui.fragmet.episodefragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.ricandmorty.core.UIState
import com.example.ricandmorty.data.model.Episodes
import com.example.ricandmorty.data.model.MainRes
import com.example.ricandmorty.data.remote.EpisodeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class EpisodeViewModel @Inject constructor(private val repository: EpisodeRepository) :
    ViewModel() {
    var liveData: LiveData<UIState<MainRes<Episodes>>> = MutableLiveData()

    fun getEpisode(page: Int){
        liveData = repository.getEpisode(page)
    }
}