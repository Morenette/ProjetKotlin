package com.example.projetkotlin.presentation.main

import androidx.annotation.RestrictTo
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.projetkotlin.domain.entity.User
import com.example.projetkotlin.domain.usecase.CreateUserUseCase
import com.example.projetkotlin.domain.usecase.GetUserUseCase
import kotlinx.coroutines.*
import org.koin.core.KoinApplication.Companion.init

class MainViewModel(
    private val createUserUseCase: CreateUserUseCase,
    private val getUserUseCase: GetUserUseCase
) : ViewModel () {

    val loginLiveData: MutableLiveData<LoginStatus> = MutableLiveData()

    fun onClickedLogin(emailUser: String, password: String){
        viewModelScope.launch(Dispatchers.IO) {
            val user = getUserUseCase.invoke(emailUser)
            val loginStatus = if (user != null){
                LoginSuccess(user.email)
            } else {
                LoginError
            }
            withContext(Dispatchers.Main){
                loginLiveData.value = loginStatus
            }
        }
    }
}