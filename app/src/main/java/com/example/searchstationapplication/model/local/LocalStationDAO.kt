package com.example.searchstationapplication.model.local

import androidx.room.*
import com.example.searchstationapplication.model.dto.SubWayStation

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
}