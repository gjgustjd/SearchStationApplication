package com.example.searchstationapplication.activity.search

import android.util.Log
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
    var allStationList: List<SubWayStation>? = null
    val searchedStationList = MutableLiveData<List<SubWayStation>>(null)

    init {
        setupSearchedData()
    }

    private fun setupSearchedData() {
        viewModelScope.launch {
            val response = repository.getSubwayStationData()
            if (response.isSuccessful) {
                allStationList = response.body()!!.subway_stations
                searchedStationList.value = listOf()
            } else {
                Log.e("setupSearchedData", response.errorBody()!!.source().toString())
            }
        }
    }

    fun performSearch(text: String) {
        searchedStationList.value =
            if (text.isNullOrBlank())
                listOf()
            else
                allStationList!!.filter { it.name.contains(text) }
    }

    fun saveStation(item: SubWayStation) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.putStationData(item)
        }
    }
}