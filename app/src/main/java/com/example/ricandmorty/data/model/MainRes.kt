package com.example.ricandmorty.data.model

import androidx.annotation.Dimension
import com.google.gson.annotations.SerializedName


data class MainRes<T>(
    val info:Info,
    val results: List<T>
)
data class Info(
    val count:Int,
    val pages: Int,
    val next: String,
    val prev: String
)
data class Characters(
    val id: Int,
    val image: String,
    val name:String,
    val species: String,
    val gender: String,
    val origin: Origin
)
data class Origin(
    val name: String,
    val url: String
)
data class Location(
    val dimension: String,
    val id: Int,
    val name: String,
    val episode: String
)
data class Episodes(
    val name: String,
    val url: String,
    @SerializedName("air_date")
    val airDate: String,
    val episode: String
)
