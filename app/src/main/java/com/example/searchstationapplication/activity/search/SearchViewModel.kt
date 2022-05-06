package com.example.searchstationapplication.activity.search

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.searchstationapplication.common.SoundSearcher
import com.example.searchstationapplication.model.MainRepository
import com.example.searchstationapplication.model.dto.ApiResponse
import com.example.searchstationapplication.model.dto.ApiResponse.*
import com.example.searchstationapplication.model.dto.SubWayStation
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject
import kotlin.Exception

@HiltViewModel
class SearchViewModel @Inject constructor(private val repository: MainRepository) : ViewModel() {
    private var allStationList: List<SubWayStation>? = null
    val searchedStationList = MutableLiveData<ApiResponse<List<SubWayStation>>>(null)

    init {
        setupSearchedData()
    }

    private fun setupSearchedData() {
        viewModelScope.launch {
            searchedStationList.value =
                try {
                    val response = repository.getSubwayStationData()
                    if (response.isSuccessful) {
                        allStationList = response.body()!!.subway_stations
                        Success(listOf())
                    } else {
                        Failure(response.errorBody()!!.source().toString())
                    }
                } catch (e: Exception) {
                    ApiResponse.Exception(e)
                }
        }
    }

    fun doSearch(text: String) {
        searchedStationList.value = Success(
            if (text.isNullOrBlank())
                listOf()
            else
                allStationList!!.filter {
                    SoundSearcher.matchString(it.name,text) })
    }

    suspend fun saveStation(item: SubWayStation) =
        withContext(viewModelScope.coroutineContext + Dispatchers.IO) {
            try {
                repository.putStationData(item)
                Success<Nothing>()
            } catch (e: Exception) {
                Exception(e)
            }
        }
}