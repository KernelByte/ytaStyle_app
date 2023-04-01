package com.example.ytastyle_app.io

import com.example.ytastyle_app.io.response.LoginResponse
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiService {
    @POST(value = "login")

    // Metodo abstracto
    fun postLogin(@Query(value = "email") email: String,@Query(value = "password") password: String) :
            Call<LoginResponse>

    companion object Factory {
        private  const val BASE_URL = "http://localhost:3000/api/v1/auth/login"
        fun create(): ApiService{
            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            return retrofit.create(ApiService::class.java)
        }
    }
}