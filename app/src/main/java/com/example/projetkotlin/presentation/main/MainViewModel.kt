package com.example.projetkotlin.presentation.main

import androidx.annotation.RestrictTo
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.projetkotlin.domain.entity.User
import com.example.projetkotlin.domain.usecase.CreateUserUseCase
import com.example.projetkotlin.domain.usecase.GetUserUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.koin.core.KoinApplication.Companion.init

class MainViewModel(
    private val createUserUseCase: CreateUserUseCase,
    private val getUserUseCase: GetUserUseCase
) : ViewModel () {

    val counter: MutableLiveData<Int> = MutableLiveData()

    init {
        counter.value = 0
    }

    fun onClickedIncrement(emailUser: String){
        viewModelScope.launch(Dispatchers.IO) {
            createUserUseCase.invoke(User("test"))
            val user = getUserUseCase.invoke("test")
        }
    }
}