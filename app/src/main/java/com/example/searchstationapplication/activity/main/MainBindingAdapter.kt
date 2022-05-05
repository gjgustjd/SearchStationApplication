package com.example.searchstationapplication.activity.main

import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.searchstationapplication.model.dto.ApiResponse
import com.example.searchstationapplication.model.dto.SubWayStation
import com.google.android.flexbox.FlexWrap
import com.google.android.flexbox.FlexboxLayoutManager
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


object MainBindingAdapter {

    @BindingAdapter("BindMainStationData", "BindViewModel")
    @JvmStatic
    fun setupRecyclerSearch(
        view: RecyclerView,
        stationList: List<SubWayStation>,
        viewModel: MainViewModel
    ) {
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

    @BindingAdapter("deleteAllViewModel")
    @JvmStatic
    fun deleteAllStation(view: View, viewModel: MainViewModel) {
        view.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                val result = viewModel.deleteAll()
                launch(Dispatchers.Main) {
                    if (result is ApiResponse.Exception<*>) {
                        Log.e("deleteStation", result.throwable.stackTraceToString())
                        Toast.makeText(view.context, "삭제에 실패하였습니다.", Toast.LENGTH_SHORT)
                            .show()
                    }
                }
            }
        }
    }

    @BindingAdapter("BindSubWayStation", "BindViewModel")
    @JvmStatic
    fun deleteStation(view: View, item: SubWayStation, viewModel: MainViewModel) {
        view.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                val result = viewModel.deleteStation(item)
                launch(Dispatchers.Main) {
                    if (result is ApiResponse.Exception<*>) {
                        Log.e("deleteStation", result.throwable.stackTraceToString())
                        Toast.makeText(view.context, "삭제에 실패하였습니다.", Toast.LENGTH_SHORT)
                            .show()
                    }
                }
            }
        }
    }
}