package com.example.projetkotlin.domain.usecase

import com.example.projetkotlin.data.repository.UserRepository
import com.example.projetkotlin.domain.entity.User

class GetUserUseCase (
    private val userRepository: UserRepository
) {
    suspend fun invoke(email: String) : User? {
        return userRepository.getUser(email)
    }
}