package com.example.projetkotlin.presentation.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.projetkotlin.R
import org.koin.android.ext.android.inject

class ListActivity : AppCompatActivity() {

    val mainViewModel: MainViewModel by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.recyclerview)

        data class ExampleItem(val imageResource: Int, val text1: String, val text2: String)
    }
}