package com.example.searchstationapplication.activity.main

import android.view.View
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.searchstationapplication.model.dto.SubWayStation
import com.google.android.flexbox.FlexWrap
import com.google.android.flexbox.FlexboxLayoutManager


object MainBindingAdapter {

    @BindingAdapter("BindMainStationData", "BindViewModel")
    @JvmStatic
    fun setupRecyclerSearch(
        view: RecyclerView,
        stationList: List<SubWayStation>?,
        viewModel: MainViewModel?
    ) {
        if (stationList != null && viewModel != null) {
            val layoutManager = FlexboxLayoutManager(view.context)
            layoutManager.flexWrap = FlexWrap.WRAP

            view.adapter = RecyclerMainStationsListAdapter(stationList, viewModel)
            view.layoutManager = (layoutManager)
        }
    }

    @BindingAdapter("BindSubWayStation", "BindViewModel")
    @JvmStatic
    fun deleteStation(view: View, item: SubWayStation?, viewModel: MainViewModel?) {
        if (viewModel != null && item != null) {
            view.setOnClickListener {
                viewModel.deleteStation(item)
            }
        }

    }
}