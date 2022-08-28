package com.example.projectxtraining.ui.training

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.projectxtraining.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    // TODO https://medium.com/android-news/login-and-main-activity-flow-a52b930f8351

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

}