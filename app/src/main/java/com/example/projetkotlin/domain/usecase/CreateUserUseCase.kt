package com.example.projetkotlin.domain.usecase

import com.example.projetkotlin.data.repository.UserRepository
import com.example.projetkotlin.domain.entity.User

class CreateUserUseCase(
    private val userRepository: UserRepository
) {
    suspend fun invoke(user: User){
        userRepository.createUser(user)
    }
}