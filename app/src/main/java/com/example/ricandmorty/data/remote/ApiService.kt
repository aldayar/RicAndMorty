package com.example.ricandmorty.data.remote

import com.example.ricandmorty.data.model.MainRes
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("character")
    fun getCharacters(@Query("page") page: Int? = 1): Call<MainRes>

    @GET("character{id}")
    fun getCharacterById(@Path("id") id: Int): Call<MainRes>
}