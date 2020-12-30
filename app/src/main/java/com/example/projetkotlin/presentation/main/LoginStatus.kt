package com.example.projetkotlin.presentation.main

import android.provider.ContactsContract

sealed class LoginStatus

data class LoginSuccess(val email: String, val password: String) : LoginStatus()
object LoginError : LoginStatus()