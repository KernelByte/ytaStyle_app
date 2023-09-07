package com.kernelbyte.ytastyle_app

import com.kernelbyte.ytastyle_app.models.LoginResponse
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiService {

    @POST(value = "login")
    fun postLogin(@Query(value = "mail") mail: String, @Query(value = "password") password: String):
            Call<LoginResponse>


    companion object Factory{
        private const val BASE_URL = "http://192.168.18.117:5000/"
        fun create():ApiService{
            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            return retrofit.create(ApiService::class.java)
        }
    }
}