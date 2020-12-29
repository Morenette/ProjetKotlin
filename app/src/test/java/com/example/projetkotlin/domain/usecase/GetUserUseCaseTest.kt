package com.example.projetkotlin.domain.usecase


import com.example.projetkotlin.data.repository.UserRepository
import com.example.projetkotlin.domain.entity.User
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.runBlocking
import com.example.projetkotlin.domain.usecase.GetUserUseCase
import org.junit.Test

class GetUserUseCaseTest {
    private val userRepository: UserRepository = mockk()

    private val classUnderTest = GetUserUseCase(userRepository)

    @Test
    fun `invoke with invalid email`() {
        runBlocking {
            val email = "a@a.c"
            coEvery { userRepository.getUser(email) } returns null

            val result = classUnderTest.invoke(email)

            coVerify(exactly = 1) { userRepository.getUser(email) }
            assertEquals(result,null)
        }
    }

    fun `invoke with valid email`() {
        runBlocking {
            val email = "a@a.c"
            val exceptedUser = User("a@a.c")
            coEvery { userRepository.getUser(email) } returns exceptedUser

            val result = classUnderTest.invoke(email)

            coVerify(exactly = 1) { userRepository.getUser(email) }
            assertEquals(result, exceptedUser)
        }
    }
}