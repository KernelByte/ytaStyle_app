package com.example.ytastyle_app.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
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

        val tvRegistrarCuenta = findViewById<TextView>(R.id.tv_crear_cuenta);
        tvRegistrarCuenta.setOnClickListener{
            goToRegister();
        }
    }

    private fun goToRegister(){
        val i = Intent(this,RegisterActivity::class.java);
        startActivity(i);
    }


    private fun goToMenu(){
        //val i = Intent(this,MenuActivity::class.java)
        //startActivity(i);
        //finish()
    }

    private fun performLogin(){
        val email = findViewById<EditText>(R.id.txt_email).text.toString().trim();
        val password = findViewById<EditText>(R.id.txt_password).text.toString().trim();

        val call = apiService.postLogin(email,password)
        call.enqueue(object: Callback<LoginResponse> {
            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                if (response.isSuccessful){
                    val loginResponse = response.body()
                    //Toast.makeText(applicationContext, loginResponse.toString(), Toast.LENGTH_SHORT).show()
                    if(loginResponse == null){
                        Toast.makeText(applicationContext, "Se produjo un error en el servidor", Toast.LENGTH_SHORT).show()
                        return
                    }
                    if (loginResponse.success){
                        Toast.makeText(applicationContext, "Funciono", Toast.LENGTH_SHORT).show()
                        //goToMenu()

                    } else {
                        Toast.makeText(applicationContext, "Contraseña o usuario incorrecto", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    Toast.makeText(applicationContext, "Se produjo un error en el servidor", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                Toast.makeText(applicationContext, t.toString(), Toast.LENGTH_SHORT).show()
            }

        })
    }
}