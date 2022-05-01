package com.example.searchstationapplication.model

import javax.inject.Inject

class MainRepository @Inject constructor(private val api: SubwayStationAPI) {
    companion object {
        val BASE_URL = "https://teacher.tictoccroc-devtest.com/"
    }

    suspend fun getSubwayStationData() = api.getSubWayStationData()
}