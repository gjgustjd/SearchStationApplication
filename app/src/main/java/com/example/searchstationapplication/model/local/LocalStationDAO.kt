package com.example.searchstationapplication.model.local

import androidx.room.*
import com.example.searchstationapplication.model.dto.SubWayStation
import kotlinx.coroutines.flow.Flow

@Dao
interface LocalStationDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(item: SubWayStation)

    @Update
    suspend fun update(item: SubWayStation)

    @Delete
    suspend fun delete(item: SubWayStation)

    @Query("DELETE FROM subway_station")
    suspend fun deleteAll()

    @Query("SELECT * FROM subway_station")
    fun getAllStations(): Flow<List<SubWayStation>>
}