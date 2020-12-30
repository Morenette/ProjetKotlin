package com.example.projetkotlin.presentation.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.projetkotlin.R
import com.example.projetkotlin.domain.entity.User
import com.example.projetkotlin.domain.usecase.CreateUserUseCase
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import io.mockk.impl.log.Logger.Companion.invoke
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.registerpage.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import java.util.Observer

class RegisterActivity : AppCompatActivity() {

    val mainViewModel: MainViewModel by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.registerpage)

        mainViewModel.createLiveData.observe(this, androidx.lifecycle.Observer {
            when(it) {
                is CreateSuccess -> {
                        var toast:Toast = Toast.makeText(this,"Utilisateur ajouté",Toast.LENGTH_LONG)
                        toast.show()
                        mainViewModel.createAccount(email_register_edit.text.toString().trim(), password_register_edit.text.toString().trim())
                        val intent: Intent = Intent(this,com.example.projetkotlin.presentation.main.MainActivity::class.java)
                        startActivity(intent)
                }
                is CreateError -> {
                    MaterialAlertDialogBuilder(this)
                        .setTitle("Erreur")
                        .setMessage("Erreur lors de la création")
                        .setPositiveButton("Ok") {
                                dialog, which -> dialog.dismiss()
                        }
                        .show()
                }
            }
        })
        creation_button.setOnClickListener{
            mainViewModel.onClickedCreate(email_register_edit.text.toString().trim(), password_register_edit.text.toString().trim())
        }
    }
}

