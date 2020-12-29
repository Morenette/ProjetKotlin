package com.example.projetkotlin.data.repository

import com.example.projetkotlin.data.local.DatabaseDao
import com.example.projetkotlin.data.local.models.UserLocal
import com.example.projetkotlin.data.local.models.toData
import com.example.projetkotlin.data.local.models.toEntity
import com.example.projetkotlin.domain.entity.User

class UserRepository(
    private val databaseDao: DatabaseDao
) {

    suspend fun createUser(user: User){
        databaseDao.insert(user.toData())
    }

    fun getUser(email: String) : User? {
        val userLocal: UserLocal? = databaseDao.findByName(email)
        return userLocal?.toEntity()
    }

}