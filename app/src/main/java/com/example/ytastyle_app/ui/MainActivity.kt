package com.example.ytastyle_app.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.ytastyle_app.MenuActivity
import com.example.ytastyle_app.R
import com.example.ytastyle_app.io.ApiService
import com.example.ytastyle_app.io.response.LoginResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity() {

    private val apiService: ApiService by lazy {
        ApiService.create()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnInicio = findViewById<Button>(R.id.btn_iniciar);
        btnInicio.setOnClickListener{
            //goToMenu()
            performLogin()
        }
    }



    private fun goToMenu(){
        val i = Intent(this,MenuActivity::class.java)
        startActivity(i);
        finish()
    }

    private fun performLogin(){
        val email = findViewById<EditText>(R.id.txt_email).text.toString();
        val password = findViewById<EditText>(R.id.txt_password).text.toString();

        val call = apiService.postLogin(email,password)
        call.enqueue(object: Callback<LoginResponse> {
            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                if (response.isSuccessful){
                    val loginResponse = response.body()
                    if(loginResponse == null){
                        Toast.makeText(applicationContext, "Se produjo un error en el servidor", Toast.LENGTH_SHORT).show()
                        return
                    }
                    if (loginResponse.success){
                        goToMenu()
                    } else {
                        Toast.makeText(applicationContext, "Contraseña o usuario incorrecto", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    Toast.makeText(applicationContext, "Se produjo un error en el servidor", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                Toast.makeText(applicationContext, "Se produjo un error en el servidor", Toast.LENGTH_SHORT).show()
            }

        })
    }
}