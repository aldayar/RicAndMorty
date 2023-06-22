package com.example.ricandmorty.ui.fragmet.locationfragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.ricandmorty.core.UIState
import com.example.ricandmorty.data.model.Location
import com.example.ricandmorty.data.model.MainRes
import com.example.ricandmorty.data.remote.LocationRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LocationViewModel @Inject constructor( private val repository: LocationRepository): ViewModel(){

    var liveData: LiveData<UIState<MainRes<Location>>> = MutableLiveData()

    fun getLocation(page: Int){
        liveData = repository.getLocation(page)
    }
}