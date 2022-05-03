package com.example.searchstationapplication.activity.search

import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.EditText
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.searchstationapplication.model.dto.SubWayStation

object SearchBindingAdapter {

    @BindingAdapter("BindSearchStationData", "BindViewModel")
    @JvmStatic
    fun setupRecyclerSearch(
        view: RecyclerView,
        stationList: List<SubWayStation>?,
        viewModel: SearchViewModel?
    ) {
        if (stationList != null && viewModel != null) {
            view.adapter = RecyclerStationsListAdapter(stationList, viewModel)
        }
    }

    @BindingAdapter("BindViewModel")
    @JvmStatic
    fun watchEditText(editText: EditText, viewModel: SearchViewModel) {
        editText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

            override fun afterTextChanged(s: Editable?) {
                viewModel.performSearch(s.toString())
            }

        })
    }

    @BindingAdapter("BindSubWayStation", "BindViewModel")
    @JvmStatic
    fun saveStation(view: View, item: SubWayStation?, viewModel: SearchViewModel?) {
        if (viewModel != null && item != null) {
            view.setOnClickListener{
                viewModel.saveStation(item)
            }
        }

    }
}