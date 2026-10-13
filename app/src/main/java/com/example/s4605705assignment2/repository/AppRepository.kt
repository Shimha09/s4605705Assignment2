package com.example.s4605705assignment2.repository

import com.example.s4605705assignment2.model.DashboardResponse
import com.example.s4605705assignment2.model.LoginRequest
import com.example.s4605705assignment2.model.LoginResponse
import com.example.s4605705assignment2.network.ApiService
import retrofit2.Response
import javax.inject.Inject

class AppRepository @Inject constructor(
    private val apiService: ApiService
) {

    suspend fun login(
        loginRequest: LoginRequest
    ): Response<LoginResponse> {
        return apiService.login(loginRequest)
    }

    suspend fun getDashboard(
        keypass: String
    ): Response<DashboardResponse> {
        return apiService.getDashboard(keypass)
    }
}