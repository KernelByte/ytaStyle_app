package com.kernelbyte.ytastyle_app

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.kernelbyte.ytastyle_app.databinding.ActivityMainBinding

private lateinit var binding: ActivityMainBinding

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val preferences = getSharedPreferences("Session",Context.MODE_PRIVATE)
        //if(preferences["jwt",""].cont)
    }
}