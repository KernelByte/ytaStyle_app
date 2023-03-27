package com.example.ytastyle_app.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.ytastyle_app.io.ApiService
import com.example.ytastyle_app.R
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // se crea el objeto de retrofit y agrega la base de la url para conectarse al endpoint
        val retrofit = Retrofit.Builder()
            .baseUrl("http://localhost:3000/api/v1")
            // Conversion de JSON a objeto class
            .addConverterFactory(GsonConverterFactory.create())
            // Construir
            .build()

        val api = retrofit.create(ApiService::class.java)
    }
}