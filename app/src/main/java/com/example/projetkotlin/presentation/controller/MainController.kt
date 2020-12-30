package com.example.projetkotlin.presentation.controller

import android.content.SharedPreferences
import com.example.projetkotlin.presentation.main.MainActivity
import com.example.projetkotlin.presentation.model.Pokemon
import com.example.projetkotlin.presentation.model.RestPokemonResponse
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainController(
    private val view: MainActivity,
    private val gson: Gson,
    var sharedPreferences: SharedPreferences
) {
    fun onStart() {
        val pokemonList = dataFromCache
        if (pokemonList != null) {
            view.showList(pokemonList)
        } else {
            makeApiCall()
        }
    }

    private fun makeApiCall() {
        val call: Call<RestPokemonResponse> = Singletons.getPokeApi().getPokemonResponse()
        call.enqueue(object : Callback<RestPokemonResponse?>() {
            fun onResponse(
                call: Call<RestPokemonResponse?>?,
                response: Response<RestPokemonResponse?>
            ) {
                if (response.isSuccessful() && response.body() != null) {
                    val pokemonList: List<Pokemon> = response.body().getResults()
                    view.showList(pokemonList)
                    savedList(pokemonList)
                } else {
                    view.showError()
                }
            }

            fun onFailure(call: Call<RestPokemonResponse?>?, t: Throwable?) {
                view.showError()
            }
        })
    }

    private fun savedList(pokemonList: List<Pokemon>) {
        val jsonString = gson.toJson(pokemonList)
        sharedPreferences
            .edit()
            .putString("jsonPokemonList", jsonString)
            .apply()
    }

    private val dataFromCache: List<Pokemon>?
        private get() {
            val jsonPokemon =
                sharedPreferences.getString(Constants.KEY_POKEMON_LIST, "null")
            return if (jsonPokemon == null) {
                null
            } else {
                val listType: Type =
                    object : TypeToken<List<Pokemon?>?>() {}.type
                gson.fromJson(jsonPokemon, listType)
            }
        }

    fun onItemClick(pokemon: Pokemon?) {
        view.navigateToDetails(pokemon)
    }

    fun onItemAClick() {}
    fun onItemBClick() {}

}