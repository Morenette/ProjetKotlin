package com.example.projetkotlin.data.repository

import com.example.projetkotlin.presentation.model.RestPokemonResponse
import retrofit2.Call

import retrofit2.http.GET


interface PokeApi {
    @get:GET("/api/v2/pokemon")
    val pokemonResponse: Call<RestPokemonResponse?>?
}