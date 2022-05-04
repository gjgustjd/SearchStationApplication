package com.example.searchstationapplication.model.local

import androidx.room.*
import com.example.searchstationapplication.model.dto.SubWayStation
import kotlinx.coroutines.flow.Flow

@Dao
interface LocalStationDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(item: SubWayStation)

    @Update
    fun update(item: SubWayStation)

    @Delete
    fun delete(item: SubWayStation)

    @Query("DELETE FROM subway_station")
    suspend fun deleteAll()

    @Query("SELECT * FROM subway_station")
    fun getAllStations(): Flow<List<SubWayStation>>
}