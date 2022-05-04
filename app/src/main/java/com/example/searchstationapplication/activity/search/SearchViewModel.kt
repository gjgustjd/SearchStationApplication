package com.example.searchstationapplication.activity.search

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.searchstationapplication.model.MainRepository
import com.example.searchstationapplication.model.dto.SubWayStation
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(private val repository: MainRepository) : ViewModel() {
    private lateinit var stationList: List<SubWayStation>
    val searchedStationList = MutableLiveData<List<SubWayStation>>()

    fun setupSearchedData() {
        viewModelScope.launch {
            val response = repository.getSubwayStationData()
            if (response.isSuccessful) {
                stationList = response.body()!!.subway_stations
                searchedStationList.value = stationList
            }
        }
    }

    fun performSearch(text: String) {
        searchedStationList.value =
            stationList.filter { it.name.contains(text) }
    }

    fun saveStation(item: SubWayStation) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.putStationData(item)
        }
    }
}