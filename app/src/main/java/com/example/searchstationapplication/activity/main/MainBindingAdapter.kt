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

            if (view.adapter == null) {
                view.adapter = RecyclerMainStationsListAdapter(stationList, viewModel)
                view.layoutManager = layoutManager
            } else {
                val mainAdapter = view.adapter as RecyclerMainStationsListAdapter
                val isDeleted = mainAdapter.stationList.size > stationList.size
                val union = (mainAdapter.stationList + stationList).distinct()
                val intersect = mainAdapter.stationList.intersect(stationList)
                val changedItems = union - intersect
                val changedIndexes = changedItems.map { mainAdapter.stationList.indexOf(it) }
                val firstItem = changedIndexes.first()

                mainAdapter.stationList = stationList

                if (changedIndexes.size > 1) {
                    if (isDeleted)
                        mainAdapter.notifyItemRangeRemoved(
                            firstItem,
                            changedIndexes.size
                        )
                    else
                        mainAdapter.notifyItemRangeInserted(
                            firstItem,
                            changedIndexes.size
                        )
                } else {
                    if (isDeleted)
                        mainAdapter.notifyItemRemoved(firstItem)
                    else
                        mainAdapter.notifyItemInserted(firstItem)
                }
            }
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