package com.example.searchstationapplication.activity.main

import android.content.Intent
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
        binding.apply {
            viewModel = this@MainActivity.viewModel
            activity = this@MainActivity
            lifecycleOwner = this@MainActivity
        }
    }

    fun finishActivity() {
        finish()
    }

    override fun onBackPressed() {
        finishActivity()
    }

    fun goSearchActivity() {
        startActivity(Intent(this, SearchActivity::class.java))
        finish()
    }
}