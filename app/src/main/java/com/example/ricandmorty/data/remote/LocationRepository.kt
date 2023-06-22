package com.example.ricandmorty.data.remote

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.ricandmorty.core.UIState
import com.example.ricandmorty.data.model.Episodes
import com.example.ricandmorty.data.model.Location
import com.example.ricandmorty.data.model.MainRes
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class LocationRepository  @Inject constructor(private val apiService: ApiService) {
    fun getLocation(page: Int): LiveData<UIState<MainRes<Location>>> {
        val live = MutableLiveData<UIState<MainRes<Location>>>()
        live.value = UIState.Loading()
        apiService.getLocation(page).enqueue(object : Callback<MainRes<Location>> {
            override fun onResponse(
                call: Call<MainRes<Location>>, response: Response<MainRes<Location>>
            ){
                if (response.isSuccessful){
                    val result = response.body()
                    live.value = UIState.Success(data = result)
                }
            }
            override fun onFailure(call: Call<MainRes<Location>>, t: Throwable) {
                live.value = UIState.Error(t.localizedMessage?:"Current Error")
            }
        })
        return live
    }
}