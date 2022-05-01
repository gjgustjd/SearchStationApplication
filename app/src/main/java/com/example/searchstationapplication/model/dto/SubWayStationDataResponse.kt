package com.example.searchstationapplication.model.dto

data class SubWayStationDataResponse(
    val result_code: Int,
    val result_msg: String,
    val subway_lines: List<SubwayLine>,
    val subway_stations: List<SubWayStation>,
    val version: Int
)