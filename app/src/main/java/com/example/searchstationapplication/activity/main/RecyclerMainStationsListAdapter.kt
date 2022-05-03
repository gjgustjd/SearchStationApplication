package com.example.searchstationapplication.activity.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.searchstationapplication.databinding.ItemMainStationBinding
import com.example.searchstationapplication.model.dto.SubWayStation

class RecyclerMainStationsListAdapter constructor(
    private val stationList: List<SubWayStation>,
    private val viewModel: MainViewModel
) :
    RecyclerView.Adapter<RecyclerMainStationsListAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemMainStationBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(stationList[position])
    }

    override fun getItemCount(): Int {
        return stationList.size
    }

    inner class ViewHolder(val binding: ItemMainStationBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: SubWayStation) {
            binding.station = item
            binding.viewModel = viewModel
        }
    }

}