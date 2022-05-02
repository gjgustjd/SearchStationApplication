package com.example.searchstationapplication.activity.search

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import com.example.searchstationapplication.R
import com.example.searchstationapplication.activity.main.MainActivity
import com.example.searchstationapplication.databinding.ActivitySearchBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchActivity : AppCompatActivity() {
    private val viewModel: SearchViewModel by viewModels()
    private lateinit var binding:ActivitySearchBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_search)
        binding.viewModel = viewModel
        binding.activity = this
        binding.lifecycleOwner = this
        viewModel.setupSearchedData()
    }

    fun goBack()
    {
        startActivity(Intent(this,MainActivity::class.java))
        finish()
    }
}