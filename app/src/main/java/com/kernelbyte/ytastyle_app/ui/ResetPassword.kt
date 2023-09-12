package com.kernelbyte.ytastyle_app.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.kernelbyte.ytastyle_app.R
import com.kernelbyte.ytastyle_app.databinding.ActivityMainBinding
import com.kernelbyte.ytastyle_app.databinding.ActivityResetPasswordBinding


class ResetPassword : AppCompatActivity() {

    private lateinit var itemBinding: ActivityResetPasswordBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reset_password)

        itemBinding = ActivityResetPasswordBinding.inflate(layoutInflater)
        setContentView(itemBinding.root)

        itemBinding.btAtrasReset.setOnClickListener{
            goToLogin()
        }


    }



    private fun goToLogin(){
        val i = Intent(this, MainActivity::class.java)
        startActivity(i)
        finish()
    }
}