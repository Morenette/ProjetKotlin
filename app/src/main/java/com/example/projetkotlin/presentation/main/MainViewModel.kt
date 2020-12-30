package com.example.projetkotlin.presentation.main

import android.widget.Toast
import androidx.annotation.RestrictTo
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.projetkotlin.domain.entity.User
import com.example.projetkotlin.domain.usecase.CreateUserUseCase
import com.example.projetkotlin.domain.usecase.GetUserUseCase
import io.mockk.impl.log.Logger.Companion.invoke
import kotlinx.coroutines.*
import org.koin.core.KoinApplication.Companion.init

class MainViewModel(
    private val createUserUseCase: CreateUserUseCase,
    private val getUserUseCase: GetUserUseCase
) : ViewModel () {

    val loginLiveData: MutableLiveData<LoginStatus> = MutableLiveData()
    val createLiveData: MutableLiveData<CreateStatus> = MutableLiveData()

    fun onClickedCreate(createmail: String,createpassword: String){
        viewModelScope.launch(Dispatchers.IO) {
            val user = getUserUseCase.invoke(createmail,createpassword)
            val createStatus = if (user == null){
                CreateSuccess(createmail,createpassword)
            } else {
                CreateError
            }
            withContext(Dispatchers.Main){
                createLiveData.value = createStatus
            }
        }
    }

    fun createAccount(email: String,password: String){
        viewModelScope.launch(Dispatchers.IO) {
            createUserUseCase.invoke(user = User(email, password))
        }
    }

    fun onClickedLogin(emailUser: String, passwordUser: String){
        viewModelScope.launch(Dispatchers.IO) {
            val user = getUserUseCase.invoke(emailUser,passwordUser)
            val loginStatus = if (user != null){
                LoginSuccess(user.email,user.password)
            } else {
                LoginError
            }

            withContext(Dispatchers.Main){
                loginLiveData.value = loginStatus
            }
        }
    }

}