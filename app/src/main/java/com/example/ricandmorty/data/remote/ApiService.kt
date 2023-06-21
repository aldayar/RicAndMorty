package com.example.ricandmorty.data.remote

import com.example.ricandmorty.data.model.Characters
import com.example.ricandmorty.data.model.Episodes
import com.example.ricandmorty.data.model.Location
import com.example.ricandmorty.data.model.MainRes
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("character")
    fun getCharacters(@Query("page") page: Int? = 1): Call<MainRes<Characters>>

    @GET("character{id}")
    fun getCharacterById(@Path("id") id: Int): Call<MainRes<Characters>>

    @GET("location")
    fun getLocation(@Query("page")page:Int = 1):Call<MainRes<Location>>

    @GET("episode")
    fun getEpisode(@Query("page")page:Int = 1):Call<MainRes<Episodes>>
}