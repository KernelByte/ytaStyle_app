package com.kernelbyte.ytastyle_app

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.kernelbyte.ytastyle_app.databinding.ActivityMainBinding


@SuppressLint("StaticFieldLeak")
private lateinit var binding: ActivityMainBinding

class MainActivity : AppCompatActivity() {

//private val apiService : ApiService by lazy {
//    ApiService.create()
//}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Poner mismo color parte de arriba de la app
        val w = window
        w.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )

        supportActionBar?.hide()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //val preferences = getSharedPreferences("jwt",Context.MODE_PRIVATE)

        //if (preferences.contains("."))
        //    goToMenu()
    }

   // private fun goToMenu(){
   //     val i = Intent(this,MenuActivity::class.java)
   //     startActivity(i)
   // }

    /*private fun createSessionPreference(){
        val preferences = PreferenceHelper.defaultPrefs(this)
        preferences
    }*/

}