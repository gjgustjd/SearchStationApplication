package com.example.searchstationapplication.model.dto

data class SubWayStation(
    val idx: Int,
    val name: String,
    val subway_lines: List<Int>
)