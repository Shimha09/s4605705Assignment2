package com.example.s4605705assignment2.viewmodel

import com.example.s4605705assignment2.model.ArchitectureEntity
import com.example.s4605705assignment2.model.DashboardResponse
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
class DashboardViewModelTest {

    private lateinit var repository: AppRepository
    private lateinit var viewModel: DashboardViewModel

    private val testDispatcher =
        StandardTestDispatcher()

    @Before
    fun setUp() {

        Dispatchers.setMain(testDispatcher)

        repository = mockk()

        viewModel = DashboardViewModel(repository)
    }

    @After
    fun tearDown() {

        Dispatchers.resetMain()
    }

    @Test
    fun `successful dashboard request updates entities`() =
        runTest(testDispatcher) {

            val entity = ArchitectureEntity(
                name = "Eiffel Tower",
                architect = "Gustave Eiffel",
                location = "Paris, France",
                yearCompleted = 1889,
                style = "Structural Expressionism",
                height = 324,
                description = "An iron lattice tower in Paris."
            )

            val dashboardResponse = DashboardResponse(
                entities = listOf(entity),
                entityTotal = 1
            )
            

            coEvery {
                repository.getDashboard("architecture")
            } returns Response.success(dashboardResponse)

            viewModel.loadDashboard("architecture")

            advanceUntilIdle()

            assertEquals(
                1,
                viewModel.entities.value.size
            )

            assertEquals(
                "Eiffel Tower",
                viewModel.entities.value[0].name
            )

            assertNull(
                viewModel.errorState.value
            )

            coVerify(exactly = 1) {
                repository.getDashboard("architecture")
            }
        }

    @Test
    fun `failed dashboard request updates error message`() =
        runTest(testDispatcher) {

            val failedResponse =
                mockk<Response<DashboardResponse>>()

            every {
                failedResponse.isSuccessful
            } returns false

            coEvery {
                repository.getDashboard("architecture")
            } returns failedResponse

            viewModel.loadDashboard("architecture")

            advanceUntilIdle()

            assertEquals(
                "Failed to load dashboard",
                viewModel.errorState.value
            )

            assertEquals(
                0,
                viewModel.entities.value.size
            )
        }
}