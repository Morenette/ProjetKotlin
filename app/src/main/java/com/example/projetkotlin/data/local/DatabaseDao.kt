package com.example.projetkotlin.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.projetkotlin.data.local.models.UserLocal

@Dao
interface DatabaseDao {
    @Query("SELECT * FROM userLocal")
    fun getAll(): List<UserLocal>

    @Query("SELECT * FROM userLocal WHERE email AND password LIKE :email AND :password LIMIT 1")
    fun findByName(email: String, password: String): UserLocal

    @Insert
    fun insert(user: UserLocal)

    @Delete
    fun delete(user: UserLocal)
}