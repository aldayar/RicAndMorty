package com.example.ricandmorty.data.remote

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.ricandmorty.core.UIState
import com.example.ricandmorty.data.model.Characters
import com.example.ricandmorty.data.model.MainRes
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class CharacterRepository @Inject constructor(private val api: ApiService ) {

    fun getCharacters(page: Int): LiveData<UIState<MainRes<Characters>>> {
        val live = MutableLiveData<UIState<MainRes<Characters>>>()
        live.value = UIState.Loading()
        api.getCharacters(page).enqueue(object : Callback<MainRes<Characters>> {
            override fun onResponse(call: Call<MainRes<Characters>>, response: Response<MainRes<Characters>>) {
                if (response.isSuccessful) {
                    val result = response.body()
                    live.value = UIState.Success(data = result)
                }
            }

            override fun onFailure(call: Call<MainRes<Characters>>, t: Throwable) {
                live.value = UIState.Error(t.localizedMessage ?: "Current error")
            }
        })
        return live
    }

    fun getCharacterById(id: Int): MutableLiveData<UIState<MainRes<Characters>>> {
        val liveData = MutableLiveData<UIState<MainRes<Characters>>>()
        liveData.value = UIState.Loading()
        api.getCharacterById(id).enqueue(object : Callback<MainRes<Characters>> {
            override fun onResponse(call: Call<MainRes<Characters>>, response: Response<MainRes<Characters>>) {
                if (response.isSuccessful) {
                    val data = response.body()
                    liveData.value = UIState.Success(data)
                }
            }
            override fun onFailure(call: Call<MainRes<Characters>>, t: Throwable) {
                liveData.value = UIState.Error(t.message.toString())
            }
        })
        return liveData
    }
}
