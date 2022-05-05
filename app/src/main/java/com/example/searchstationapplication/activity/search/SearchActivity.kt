package com.example.searchstationapplication.activity.search

import android.content.Intent
import android.os.Bundle
import android.widget.LinearLayout.LayoutParams
import android.widget.ProgressBar
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.searchstationapplication.R
import com.example.searchstationapplication.activity.main.MainActivity
import com.example.searchstationapplication.databinding.ActivitySearchBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchActivity : AppCompatActivity() {
    private val viewModel: SearchViewModel by viewModels()
    private lateinit var binding: ActivitySearchBinding
    private val loadingDialog by lazy {
        val progressBar = ProgressBar(this)
        val lp = LayoutParams(
            LayoutParams.WRAP_CONTENT,
            LayoutParams.WRAP_CONTENT
        )
        progressBar.layoutParams = lp
        AlertDialog.Builder(this)
            .setView(progressBar)
            .setTitle("지하철 역 데이터 로딩")
            .setMessage("데이터 로딩 중입니다.")
            .create()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_search)
        binding.viewModel = viewModel
        binding.activity = this
        binding.lifecycleOwner = this
    }

    fun goBack() {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }

    fun showLoadingDialog() {
        loadingDialog.show()
    }

    fun dismissLoadingDialog()
    {
        loadingDialog.dismiss()
    }
}