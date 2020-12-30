package com.example.projetkotlin

import android.content.SharedPreferences
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object Singletons {
    private var gsonInstance: Gson? = null
    private var pokeApiInstance: PokeApi? = null
    private var sharedPreferencesInstance: SharedPreferences? = null
    val gson: Gson?
        get() {
            if (gsonInstance == null) {
                gsonInstance = GsonBuilder()
                    .setLenient()
                    .create()
            }
            return gsonInstance
        }

    val pokeApi: PokeApi?
        get() {
            if (pokeApiInstance == null) {
                val retrofit: Retrofit = Retrofit.Builder()
                    .baseUrl(Constants.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build()
                pokeApiInstance = retrofit.create<PokeApi>(PokeApi::class.java)
            }
            return pokeApiInstance
        }

    fun getSharedPreferences(context: Context): SharedPreferences? {
        if (sharedPreferencesInstance == null) {
            sharedPreferencesInstance =
                context.getSharedPreferences("app_David", Context.MODE_PRIVATE)
        }
        return sharedPreferencesInstance
    }
}