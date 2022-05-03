package com.example.searchstationapplication.model

import com.example.searchstationapplication.model.dto.SubWayStation
import com.example.searchstationapplication.model.local.LocalStationDataBase
import javax.inject.Inject

class MainRepository @Inject constructor(
    private val api: SubwayStationAPI,
    private val dataBase: LocalStationDataBase
) {
    companion object {
        val BASE_URL = "https://teacher.tictoccroc-devtest.com/"
    }

    suspend fun getSubwayStationData() = api.getSubWayStationData()

    fun putStationData(item: SubWayStation) =
        dataBase.localStationDao().insert(item)

    suspend fun deleteStationData(item: SubWayStation) = dataBase.localStationDao().delete(item)
    suspend fun deleteAll() = dataBase.localStationDao().deleteAll()
    fun getSelectedStationsAll()= dataBase.localStationDao().getAllStations()
}