package com.kernelbyte.ytastyle_app

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.kernelbyte.ytastyle_app.databinding.ActivityMainBinding
import com.kernelbyte.ytastyle_app.models.LoginResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity() {

    private lateinit var itemBinding: ActivityMainBinding
    private lateinit var  userPreferences: SharedPreferences
    private var token: String? = null

private val apiService : ApiService by lazy {
    ApiService.create()
}


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Poner mismo color parte de arriba de la app
        val w = window
        w.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )
        supportActionBar?.hide()
        itemBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(itemBinding.root)


            //token = userPreferences.getString("jwt", "")

        itemBinding.btIniciar.setOnClickListener{
            performLogin()
        }


        if (token?.contains(".") == true) {
                goToMenu()
        }
    }




    // Metodo para iniciar sesion en el aplicativo
    private fun performLogin(){
        val et_email = itemBinding.etMail.text.toString()
        val et_pass = itemBinding.etPass.text.toString()

        val call = apiService.postLogin(et_email,et_pass)

        call.enqueue(object :  Callback<LoginResponse>{
            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                if(response.isSuccessful){
                    val loginResponse = response.body()
                    if (loginResponse == null){
                        Toast.makeText(applicationContext,"Usuario o contraseña invalidos",Toast.LENGTH_SHORT).show()
                        return
                    }

                    // Si la comunicacion es correcta
                    if (loginResponse.success){
                        createSessionPreference(loginResponse.jwt)
                        goToMenu()
                        return
                    }else{
                        Toast.makeText(applicationContext,"Usuario o contraseña invalidos",Toast.LENGTH_SHORT).show()
                    }
                }  else{
                    Toast.makeText(applicationContext,"Se produjo un error en el servidor",Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                Toast.makeText(applicationContext,"Se produjo un error en el servidor",Toast.LENGTH_SHORT).show()
            }

        })

    }

    // Metodo para crear y almacenar token en preferences
    private fun createSessionPreference(jwt : String){
        userPreferences = getSharedPreferences("login", Context.MODE_PRIVATE)
        var editor = userPreferences.edit()
        editor.putString("jwt",jwt)
        editor.commit()
    }


    private fun goToMenu(){
        val i = Intent(this,MenuActivity::class.java)
        startActivity(i)
        finish()
    }
}