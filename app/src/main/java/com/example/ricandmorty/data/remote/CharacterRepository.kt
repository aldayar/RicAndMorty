package com.example.ricandmorty.data.remote

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.ricandmorty.core.UIState
import com.example.ricandmorty.data.model.MainRes
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CharacterRepository {
    private val api = getClient().create(ApiService::class.java)

    fun getCharacters(page: Int): LiveData<UIState<MainRes>> {
        val live = MutableLiveData<UIState<MainRes>>()
        live.value = UIState.Loading()
        api.getCharacters(page).enqueue(object : Callback<MainRes> {
            override fun onResponse(call: Call<MainRes>, response: Response<MainRes>) {
                if (response.isSuccessful) {
                    val result = response.body()
                    live.value = UIState.Success(data = result)
                }
            }

            override fun onFailure(call: Call<MainRes>, t: Throwable) {
                live.value = UIState.Error(t.localizedMessage ?: "Current error")
            }
        })
        return live
    }

    fun getCharacterById(id: Int): MutableLiveData<UIState<MainRes>> {
        val liveData = MutableLiveData<UIState<MainRes>>()
        liveData.value = UIState.Loading()
        api.getCharacterById(id).enqueue(object : Callback<MainRes> {
            override fun onResponse(call: Call<MainRes>, response: Response<MainRes>) {
                if (response.isSuccessful) {
                    val data = response.body()
                    liveData.value = UIState.Success(data)
                }
            }
            override fun onFailure(call: Call<MainRes>, t: Throwable) {
                liveData.value = UIState.Error(t.message.toString())
            }
        })
        return liveData
    }
}
