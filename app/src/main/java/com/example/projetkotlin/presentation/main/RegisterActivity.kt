package com.example.projetkotlin.presentation.main

import android.content.Context
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

class RegisterActivity : AppCompatActivity() {

    val mainViewModel: MainViewModel by inject()

    lateinit var handler:DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.registerpage)

        handler= DatabaseHelper(this)

        creation_button.setOnClickListener {
            handler.insertUserDAta(email_register_edit.text.toString(),password_register_edit.text.toString())
            val toast: Toast = Toast.makeText(this,"Ajouté",Toast.LENGTH_LONG)
            toast.show()
            val intent: Intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

    }
}