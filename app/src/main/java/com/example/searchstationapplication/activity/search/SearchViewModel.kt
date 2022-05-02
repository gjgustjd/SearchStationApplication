package com.example.searchstationapplication.activity.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.searchstationapplication.model.MainRepository
import com.example.searchstationapplication.model.dto.SubWayStation
import com.example.searchstationapplication.model.dto.SubWayStationDataResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(private val repository: MainRepository) : ViewModel() {
    val searchDataResponse = MutableStateFlow<SubWayStationDataResponse?>(null)
    val searchedStationList = MutableStateFlow<List<SubWayStation>?>(null)

    fun setupSearchedData() {
        viewModelScope.launch {
            val response = repository.getSubwayStationData()
            if (response.isSuccessful) {
                searchDataResponse.value = response.body()
                searchedStationList.value =searchDataResponse.value!!.subway_stations
            }
        }
    }

    fun performSearch(text: String) {
        searchedStationList.value =
            searchDataResponse.value!!.subway_stations.filter { it.name.contains(text) }
    }

}