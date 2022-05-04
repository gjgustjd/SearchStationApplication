package com.example.searchstationapplication.activity.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.searchstationapplication.databinding.ItemSearchStationBinding
import com.example.searchstationapplication.model.dto.SubWayStation

class RecyclerSearchStationsListAdapter constructor(
    private val stationList: List<SubWayStation>,
    private val viewModel: SearchViewModel
) :
    RecyclerView.Adapter<RecyclerSearchStationsListAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemSearchStationBinding.inflate(
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

    inner class ViewHolder(val binding: ItemSearchStationBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: SubWayStation) {
            binding.station = item
            binding.adapter = this@RecyclerSearchStationsListAdapter
            binding.viewModel = viewModel
        }
    }

}