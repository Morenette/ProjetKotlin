package com.example.projetkotlin.presentation.main

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.projetkotlin.Constants
import com.example.projetkotlin.R
import com.example.projetkotlin.Singletons
import com.example.projetkotlin.Singletons.gson
import com.example.projetkotlin.presentation.model.Pokemon
import com.example.projetkotlin.presentation.view.DetailsActivity
import com.example.projetkotlin.presentation.view.ListAdapter
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

        controller = new MainController(
                this,
        Singletons.getGson(),
        Singletons.getSharedPreferences(getApplicationContext())
        );

        controller.onStart();
    }

    fun showList(pokemonList: List<Pokemon?>?) {
        recyclerView = findViewById(R.id.recycler_view) as RecyclerView
        recyclerView.setHasFixedSize(true)
        layoutManager = LinearLayoutManager(this)
        recyclerView.setLayoutManager(layoutManager)
        mAdapter = ListAdapter(pokemonList, object : ListAdapter.OnItemClickListener() {
            fun onItemClick(item: Pokemon?) {
                controller.onItemClick(item)
            }
        })
        recyclerView.setAdapter(mAdapter)
    }

    fun showError() {
        Toast.makeText(applicationContext, "API Error", Toast.LENGTH_SHORT).show()
    }

    fun navigateToDetails(pokemon: Pokemon?) {
        val myIntent = Intent(this@MainActivity, DetailsActivity::class.java)
        myIntent.putExtra(Constants.POKEMON_KEY, gson!!.toJson(pokemon))
        this@MainActivity.startActivity(myIntent)
    }

}


