package com.example.searchstationapplication.activity.main

import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.EditText
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.searchstationapplication.model.dto.SubWayStation

object MainBindingAdapter {

    @BindingAdapter("BindMainStationData", "BindViewModel")
    @JvmStatic
    fun setupRecyclerSearch(
        view: RecyclerView,
        stationList: List<SubWayStation>?,
        viewModel: MainViewModel?
    ) {
        if (stationList != null && viewModel != null) {
            view.adapter = RecyclerMainStationsListAdapter(stationList, viewModel)
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