package com.example.searchstationapplication.model.local

import androidx.room.*
import com.example.searchstationapplication.model.dto.SubWayStation
import kotlinx.coroutines.flow.StateFlow

@Dao
interface LocalStationDAO {
    @Insert
    fun insert(item: SubWayStation)

    @Update
    fun update(item: SubWayStation)

    @Delete
    fun delete(item: SubWayStation)

    @Query("DELETE FROM subway_station")
    fun deleteAll()

    @Query("SELECT * FROM subway_station")
    fun getAllStations(): StateFlow<List<SubWayStation>>
}