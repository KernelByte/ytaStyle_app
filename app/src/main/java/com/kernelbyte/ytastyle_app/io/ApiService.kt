package com.kernelbyte.ytastyle_app.io

import com.kernelbyte.ytastyle_app.io.response.CreateResponse
import com.kernelbyte.ytastyle_app.io.response.LoginResponse
import com.kernelbyte.ytastyle_app.model.UserCreate
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiService {

    // Point para hacer login
    @POST(value = "login")
    fun postLogin(@Query(value = "mail") mail: String, @Query(value = "password") password: String):
            Call<LoginResponse>

    // Point para crear usuario
    @POST("users")
    fun crate_user(@Body userModel : UserCreate):
            Call<CreateResponse>

    companion object Factory{
        private const val BASE_URL = "http://192.168.18.123:5000/"
        fun create(): ApiService {
            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            return retrofit.create(ApiService::class.java)
        }
    }
}