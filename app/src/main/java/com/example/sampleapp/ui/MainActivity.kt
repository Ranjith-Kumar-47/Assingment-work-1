package com.example.sampleapp.ui

import android.os.Bundle
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.sampleapp.R
import com.example.sampleapp.databinding.ActivityMainBinding
import com.example.sampleapp.viewModel.MainActivityViewModel

class MainActivity : AppCompatActivity() {
    lateinit var mainActivityViewModel: MainActivityViewModel
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        mainActivityViewModel = ViewModelProvider(this)[MainActivityViewModel::class.java]
        val linearLayout = findViewById<LinearLayout>(R.id.linearLayout)
        binding.mainActivityViewModel = mainActivityViewModel
        binding.buttonSearch.setOnClickListener {
            mainActivityViewModel.getData(binding.progressBar, linearLayout, this)
        }
    }
}