package com.example.ricandmorty.data.remote

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

fun getClient() =
    Retrofit.Builder()
        .baseUrl("https://rickandmortyapi.com/api/")
        .addConverterFactory(GsonConverterFactory.create()).build()

