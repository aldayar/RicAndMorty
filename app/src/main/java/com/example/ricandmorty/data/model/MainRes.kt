package com.example.ricandmorty.data.model


data class MainRes(
    val info:Info,
    val results: List<Characters>
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
    val name: String,
    val episode: String
)
