package com.example.s4605705assignment2.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.s4605705assignment2.model.LoginRequest
import com.example.s4605705assignment2.repository.AppRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val repository: AppRepository
) : ViewModel() {

    private val _keypass = MutableStateFlow<String?>(null)
    val keypass: StateFlow<String?> = _keypass

    private val _errorState = MutableStateFlow<String?>(null)
    val errorState: StateFlow<String?> = _errorState

    fun login(username: String, password: String) {

        viewModelScope.launch {

            try {

                _errorState.value = null

                val loginRequest = LoginRequest(
                    username = username,
                    password = password
                )

                val response = repository.login(loginRequest)

                if (response.isSuccessful) {

                    val loginResponse = response.body()

                    if (loginResponse != null) {
                        _keypass.value = loginResponse.keypass
                    } else {
                        _errorState.value = "Login failed: Empty response"
                    }

                } else {

                    _errorState.value =
                        "Login failed. Please check your username and password."
                }

            } catch (e: Exception) {

                _errorState.value =
                    "Network error: ${e.message}"
            }
        }
    }
    fun clearKeypass() {
        _keypass.value = null
    }
}