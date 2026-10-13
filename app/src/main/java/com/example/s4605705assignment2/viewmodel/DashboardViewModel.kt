package com.example.s4605705assignment2.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.s4605705assignment2.model.ArchitectureEntity
import com.example.s4605705assignment2.repository.AppRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(
    private val repository: AppRepository
) : ViewModel() {

    private val _entities =
        MutableStateFlow<List<ArchitectureEntity>>(emptyList())

    val entities: StateFlow<List<ArchitectureEntity>> = _entities

    private val _errorState =
        MutableStateFlow<String?>(null)

    val errorState: StateFlow<String?> = _errorState

    fun loadDashboard(keypass: String) {

        viewModelScope.launch {

            try {

                _errorState.value = null

                val response =
                    repository.getDashboard(keypass)

                if (response.isSuccessful) {

                    val dashboardResponse = response.body()

                    if (dashboardResponse != null) {

                        _entities.value =
                            dashboardResponse.entities

                    } else {

                        _errorState.value =
                            "Dashboard response is empty"
                    }

                } else {

                    _errorState.value =
                        "Failed to load dashboard"
                }

            } catch (e: Exception) {

                _errorState.value =
                    "Network error: ${e.message}"
            }
        }
    }
}