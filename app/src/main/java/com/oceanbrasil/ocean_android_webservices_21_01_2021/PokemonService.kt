package com.oceanbrasil.ocean_android_webservices_21_01_2021

import retrofit2.Call
import retrofit2.http.GET

interface PokemonService {
    @GET("/pokemon")
    fun listPokemons(): Call<PokemonApiResult>
}
