package com.example.searchstationapplication.model.dto

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.searchstationapplication.model.local.CommonConverter

@Entity(tableName = "subway_station")
@TypeConverters(CommonConverter::class)
data class SubWayStation(
    @PrimaryKey val idx: Int,
    val name: String,
    val subway_lines: List<Int>
) {
}