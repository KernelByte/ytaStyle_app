package com.kernelbyte.ytastyle_app.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import android.widget.Toast
import com.kernelbyte.ytastyle_app.databinding.ActivityRegisterBinding
import com.kernelbyte.ytastyle_app.io.ApiService
import com.kernelbyte.ytastyle_app.io.response.CreateResponse
import com.kernelbyte.ytastyle_app.model.UserCreate
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class RegisterActivity : AppCompatActivity() {

    private lateinit var itemBinding: ActivityRegisterBinding

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
        itemBinding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(itemBinding.root)

        itemBinding.btCrearCuenta.setOnClickListener {
            createUser()
        }

        itemBinding.tvIniciarUsuario.setOnClickListener{
            goToLogin()
        }

    }

    // Metodo para crear un usuario nuevo
    private fun createUser(){
        val et_nombre = itemBinding.etNombreUsuario.text.toString()
        val et_correo = itemBinding.etCorreoUsuario.text.toString()
        val et_pass = itemBinding.etConUsuario.text.toString()

        val user: UserCreate = UserCreate(et_nombre, et_correo,et_pass)
        val call = apiService.crate_user(user)

        call.enqueue(object : Callback<CreateResponse>{
            override fun onResponse(
                call: Call<CreateResponse>,
                response: Response<CreateResponse>
            ) {
                if(response.isSuccessful){
                    val registerResponse = response.body()

                    if (registerResponse == null){
                        Toast.makeText(applicationContext,"Respuesta vacia del servidor",Toast.LENGTH_SHORT).show()
                        return
                    }

                    if(registerResponse.success){
                        goToLogin()
                        Toast.makeText(applicationContext,registerResponse.message.toString(), Toast.LENGTH_SHORT).show()
                    }else{
                        Toast.makeText(applicationContext,registerResponse.message.toString(),Toast.LENGTH_SHORT).show()
                    }
                }else{
                    Toast.makeText(applicationContext,"Se produjo un error en el servidor", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<CreateResponse>, t: Throwable) {
                Toast.makeText(applicationContext,"Se produjo un error en el servidor", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun goToLogin(){
        val i = Intent(this, MainActivity::class.java)
        startActivity(i)
        finish()
    }
}