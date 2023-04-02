package com.example.ytastyle_app.io

import android.provider.ContactsContract.CommonDataKinds.Email
import com.example.ytastyle_app.io.response.LoginResponse
import com.example.ytastyle_app.model.Users
import com.google.gson.GsonBuilder
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

object AppConstantes {
    const val BASE_URL = "http://192.168.18.74:3000/api/v1/";
}

interface ApiService {


    @POST("users")
    // Metodo abstracto
    suspend  fun createUser(@Body usuario: Users) : Response<String>

    companion object Factory {

        val apiService : ApiService by lazy {
            Retrofit.Builder()
                .baseUrl(AppConstantes.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
                .build().create(ApiService::class.java)
        }
    }
}