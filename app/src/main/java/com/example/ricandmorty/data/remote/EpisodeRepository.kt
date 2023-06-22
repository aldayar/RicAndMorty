package com.example.ricandmorty.data.remote

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.ricandmorty.core.UIState
import com.example.ricandmorty.data.model.Episodes
import com.example.ricandmorty.data.model.MainRes
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class EpisodeRepository @Inject constructor(private val apiService: ApiService) {
    fun getEpisode(page: Int): LiveData<UIState<MainRes<Episodes>>> {
        val live = MutableLiveData<UIState<MainRes<Episodes>>>()
        live.value = UIState.Loading()
        apiService.getEpisode(page).enqueue(object : Callback<MainRes<Episodes>> {
            override fun onResponse(
                call: Call<MainRes<Episodes>>, response: Response<MainRes<Episodes>>){
                    if (response.isSuccessful){
                        val result = response.body()
                        live.value = UIState.Success(data = result)
                    }
            }
            override fun onFailure(call: Call<MainRes<Episodes>>, t: Throwable) {
                live.value = UIState.Error(t.localizedMessage?:"Current Error")
            }
        })
        return live
    }
}