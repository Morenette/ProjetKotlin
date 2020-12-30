package com.example.projetkotlin.presentation.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import com.example.projetkotlin.R
import com.example.projetkotlin.data.local.DatabaseHelper
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.registerpage.*
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity() {

    val mainViewModel: MainViewModel by inject()

    lateinit var handler:DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        handler= DatabaseHelper(this)

        login_button.setOnClickListener {
            if (handler.userPresent(login_edit.text.toString(),password_edit.text.toString()))
                Toast.makeText(this,"Connecté", Toast.LENGTH_LONG)
            else
                Toast.makeText(this,"Informations incorrect", Toast.LENGTH_LONG)
        }

        create_account_button.setOnClickListener {
            val intent: Intent = Intent(this,RegisterActivity::class.java)
            startActivity(intent)
        }

    }
}
