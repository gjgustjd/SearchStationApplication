package com.example.searchstationapplication.activity.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.searchstationapplication.model.MainRepository
import com.example.searchstationapplication.model.dto.ApiResponse
import com.example.searchstationapplication.model.dto.SubWayStation
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repository: MainRepository) : ViewModel() {
    val selectedStations = repository.getSelectedStationsAll()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.Eagerly,
            initialValue = listOf()
        )

    suspend fun deleteAll() =
        withContext(viewModelScope.coroutineContext + Dispatchers.IO) {
            try {
                repository.deleteAll()
                ApiResponse.Success()
            } catch (e: Exception) {
                ApiResponse.Exception<Nothing>(e)
            }
        }

    suspend fun deleteStation(station: SubWayStation) =
        withContext(viewModelScope.coroutineContext + Dispatchers.IO) {
            try {
                repository.deleteStationData(station)
                ApiResponse.Success()
            } catch (e: Exception) {
                ApiResponse.Exception<Nothing>(e)
            }
        }
}