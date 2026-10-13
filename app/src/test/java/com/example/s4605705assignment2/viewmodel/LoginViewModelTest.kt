package com.example.s4605705assignment2.viewmodel

import com.example.s4605705assignment2.model.LoginRequest
import com.example.s4605705assignment2.model.LoginResponse
import com.example.s4605705assignment2.repository.AppRepository
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Before
import org.junit.Test
import retrofit2.Response

@OptIn(ExperimentalCoroutinesApi::class)
class LoginViewModelTest {

    private lateinit var repository: AppRepository
    private lateinit var viewModel: LoginViewModel

    private val testDispatcher =
        StandardTestDispatcher()

    @Before
    fun setUp() {

        Dispatchers.setMain(testDispatcher)

        repository = mockk()

        viewModel = LoginViewModel(repository)
    }

    @After
    fun tearDown() {

        Dispatchers.resetMain()
    }

    @Test
    fun `successful login updates keypass`() =
        runTest(testDispatcher) {

            coEvery {
                repository.login(
                    LoginRequest(
                        "4605705",
                        "Shimha"
                    )
                )
            } returns Response.success(
                LoginResponse("architecture")
            )

            viewModel.login(
                "4605705",
                "Shimha"
            )

            advanceUntilIdle()

            assertEquals(
                "architecture",
                viewModel.keypass.value
            )

            assertNull(
                viewModel.errorState.value
            )

            coVerify(exactly = 1) {
                repository.login(
                    LoginRequest(
                        "4605705",
                        "Shimha"
                    )
                )
            }
        }

    @Test
    fun `failed login updates error message`() =
        runTest(testDispatcher) {

            val failedResponse =
                mockk<Response<LoginResponse>>()

            every {
                failedResponse.isSuccessful
            } returns false

            coEvery {
                repository.login(any())
            } returns failedResponse

            viewModel.login(
                "4605705",
                "WrongName"
            )

            advanceUntilIdle()

            assertEquals(
                "Login failed. Please check your username and password.",
                viewModel.errorState.value
            )

            assertNull(
                viewModel.keypass.value
            )
        }
}