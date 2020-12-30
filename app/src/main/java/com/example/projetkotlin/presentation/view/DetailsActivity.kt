package com.example.projetkotlin.presentation.view

import android.R
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.projetkotlin.Singletons.gson
import com.example.projetkotlin.presentation.model.Pokemon


class DetailsActivity : AppCompatActivity() {
    private var txtDetail: TextView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.detail_activity)
        txtDetail = findViewById(R.id.detail_txt)
        val intent = intent
        val pokemonJson = intent.getStringExtra(Constants.POKEMON_KEY)
        val pokemon = gson!!.fromJson(pokemonJson, Pokemon::class.java)
        showDetail(pokemon)
    }

    private fun showDetail(pokemon: Pokemon) {
        txtDetail!!.text = pokemon.name
    }
}