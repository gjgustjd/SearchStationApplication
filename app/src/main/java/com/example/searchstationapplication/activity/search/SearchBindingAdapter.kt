package com.example.searchstationapplication.activity.search

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.searchstationapplication.model.dto.SubWayStation

object SearchBindingAdapter {

    @BindingAdapter("BindSearchStationData")
    @JvmStatic
    fun setupRecyclerSearch(view: RecyclerView, stationList: List<SubWayStation>?) {
        stationList?.let {
            view.adapter = RecyclerStationsListAdapter(stationList)
        }
    }
}