package com.example.searchstationapplication.activity.search

import android.app.Activity
import android.content.Intent
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.searchstationapplication.model.dto.ApiResponse.*
import com.example.searchstationapplication.activity.main.MainActivity
import com.example.searchstationapplication.model.dto.ApiResponse
import com.example.searchstationapplication.model.dto.SubWayStation

object SearchBindingAdapter {

    @BindingAdapter("BindSearchStationData", "BindViewModel", "BindActivity")
    @JvmStatic
    fun setupRecyclerSearch(
        view: RecyclerView,
        stationList: ApiResponse<List<SubWayStation>>?,
        viewModel: SearchViewModel?,
        activity: SearchActivity?
    ) {
        fun onFail(message: String, source: String) {
            Log.e("setupRecyclerSearch", source)
            Toast.makeText(activity, message, Toast.LENGTH_SHORT).show()
            activity!!.goBack()
        }

        if (stationList == null)
            activity!!.showLoadingDialog()
        else {
            if (viewModel != null) {
                activity!!.dismissLoadingDialog()
                when (stationList) {
                    is Success ->
                        view.adapter =
                            RecyclerSearchStationsListAdapter(stationList.data, viewModel)
                    is Failure ->
                        onFail("데이터 수신에 실패했습니다.", stationList.message)
                    is Exception ->
                        onFail("오류가 발생했습니다.", stationList.throwable.stackTraceToString())
                }
            }
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
            view.setOnClickListener {
                val baseActivity = view.context as Activity

                viewModel.saveStation(item)
                baseActivity.startActivity(Intent(baseActivity, MainActivity::class.java))
                baseActivity.finish()
            }
        }

    }
}