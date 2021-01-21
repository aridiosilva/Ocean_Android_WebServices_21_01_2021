package com.oceanbrasil.ocean_android_webservices_21_01_2021

data class PokemonApiResult(
    val count: Int,
    val next: String,
    val previous: String,
    val results: List<PokemonResult>
)

data class PokemonResult(
    val name: String,
    val url: String
)
