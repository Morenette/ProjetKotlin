package com.example.projetkotlin.presentation.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.projetkotlin.R
import kotlinx.android.synthetic.main.recyclerview.*
import org.koin.android.ext.android.inject
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ListActivity : AppCompatActivity() {

    val mainViewModel: MainViewModel by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.recyclerview)

        val retrofit = Retrofit.Builder()
            .baseUrl("")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        recycler_view.apply {
            layoutManager = LinearLayoutManager(this@ListActivity)
        }

        data class ExampleItem(val imageResource: Int, val text1: String, val text2: String)
    }
}