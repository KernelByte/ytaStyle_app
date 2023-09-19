package com.kernelbyte.ytastyle_app.ui



import android.content.Intent
import android.os.Bundle
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.kernelbyte.ytastyle_app.io.ApiService
import com.kernelbyte.ytastyle_app.databinding.ActivityMainBinding
import com.kernelbyte.ytastyle_app.io.response.LoginResponse
import com.kernelbyte.ytastyle_app.model.UserLogin
import com.kernelbyte.ytastyle_app.util.PreferenceHelper
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity() {

    private lateinit var itemBinding: ActivityMainBinding
    private lateinit var userPreferences: PreferenceHelper

    private val apiService : ApiService by lazy {
        ApiService.create()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Verificacion de preferencias
        userPreferences = PreferenceHelper(this)
        if(userPreferences.getString("jwt","").contains("."))
            goToMenu()

        // Poner mismo color parte de arriba de la app
        val w = window
        w.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )
        supportActionBar?.hide()
        itemBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(itemBinding.root)

        itemBinding.btIniciar.setOnClickListener{
            performLogin()
        }

        itemBinding.ibCorreoLogin.setOnClickListener{
            goToRegister()
        }

        itemBinding.tvOlvido.setOnClickListener {
            goToResetPassword()
        }
    }

    // Metodo para iniciar sesion en el aplicativo
    private fun performLogin(){
        val et_email = itemBinding.etMail.text.toString()
        val et_pass = itemBinding.etPass.text.toString()

        val user: UserLogin = UserLogin(et_email,et_pass)
        val call = apiService.postLogin(user)

        call.enqueue(object : Callback<LoginResponse>{
            override fun onResponse(
                call: Call<LoginResponse>,
                response: Response<LoginResponse>
            ) {
                if(response.isSuccessful){
                    val loginResponse = response.body()
                    if (loginResponse == null){
                        Toast.makeText(applicationContext,"Usuario o contraseña invalidos",Toast.LENGTH_SHORT).show()
                        return
                    }

                    // Si la comunicacion es correcta
                    if (loginResponse.success){
                        createSessionPreference("jwt", loginResponse.data.jwt)
                        createSessionPreference("nombre", loginResponse.data.user.nameUser)
                        createSessionPreference("correo", loginResponse.data.user.mailUser)
                        goToMenu()
                        return
                    }else{
                        Toast.makeText(applicationContext,"Usuario o contraseña invalidos",Toast.LENGTH_SHORT).show()
                    }
                }  else{
                    Toast.makeText(applicationContext,"Se produjo un error en el servidor.",Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                Toast.makeText(applicationContext,"Error: Se produjo un error en el servidor",Toast.LENGTH_SHORT).show()
            }
        })
    }

    // Metodo para crear y almacenar token en userPreferences
    private fun createSessionPreference(clave:  String, jwt : String){
        //val userPreferences = PreferenceHelper(this)
        userPreferences.saveString(clave, jwt)
    }

    private fun goToMenu(){
        val i = Intent(this, MenuActivity::class.java)
        startActivity(i)
        finish()
    }

    private fun goToRegister(){
        val i = Intent(this, RegisterActivity::class.java)
        startActivity(i)
        finish()
    }

    private fun goToResetPassword(){
        val i = Intent(this, ResetPassword::class.java)
        startActivity(i)
        finish()
    }
}