package com.example.searchstationapplication.model.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.searchstationapplication.model.dto.SubWayStation

@Database(entities = [SubWayStation::class], version = 3)
abstract class LocalStationDataBase : RoomDatabase() {
    abstract fun localStationDao(): LocalStationDAO
}