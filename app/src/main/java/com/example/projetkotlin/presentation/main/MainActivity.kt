package com.example.projetkotlin.presentation.main

import android.app.ListActivity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import com.example.projetkotlin.R
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity() {

    val mainViewModel: MainViewModel by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainViewModel.loginLiveData.observe(this, Observer {
            when(it){
                is LoginSuccess -> {
                    var toast:Toast = Toast.makeText(this,"Connection réussi",Toast.LENGTH_LONG)
                    toast.show()
                    val intent: Intent = Intent(this,com.example.projetkotlin.presentation.main.ListActivity::class.java)
                    startActivity(intent)
                }
                is LoginError -> {
                    var toast:Toast = Toast.makeText(this,"Echec de la Connection",Toast.LENGTH_LONG)
                    toast.show()
                     MaterialAlertDialogBuilder(this)
                     .setTitle("Erreur")
                         .setMessage("Compte Inconnu")
                         .setPositiveButton("Ok") {
                             dialog, which -> dialog.dismiss()
                         }
                         .show()
                }
            }
        })

        login_button.setOnClickListener {
            mainViewModel.onClickedLogin(login_edit.text.toString(), password_edit.text.toString())
        }

        create_account_button.setOnClickListener {
            val intent: Intent = Intent(this,RegisterActivity::class.java)
            startActivity(intent)
        }

    }
}
