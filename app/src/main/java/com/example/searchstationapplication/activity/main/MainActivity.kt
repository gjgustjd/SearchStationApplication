package com.example.searchstationapplication.activity.main

import android.content.Intent
import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import com.example.searchstationapplication.R
import com.example.searchstationapplication.activity.search.SearchActivity
import com.example.searchstationapplication.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val viewModel: MainViewModel by viewModels()
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.viewModel = viewModel
        binding.activity = this
        binding.lifecycleOwner = this
    }

    fun finishActivity() {
        finish()
    }

    override fun onBackPressed() {
        finishActivity()
    }

    fun goSearchActivity() {
        startActivity(Intent(this, SearchActivity::class.java))
    }
}