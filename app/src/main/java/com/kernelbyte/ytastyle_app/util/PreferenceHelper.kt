package com.kernelbyte.ytastyle_app.util

import android.content.Context
import android.content.SharedPreferences

class PreferenceHelper(context: Context) {

    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences("MiAppPreferences", Context.MODE_PRIVATE)

    // Método para guardar un valor en las preferencias compartidas
    fun saveString(key: String, value: String) {
        val editor = sharedPreferences.edit()
        editor.putString(key, value)
        editor.apply()
    }

    // Método para recuperar un valor de las preferencias compartidas (con un valor predeterminado si no existe)
    fun getString(key: String, defaultValue: String = ""): String {
        return sharedPreferences.getString(key, defaultValue) ?: defaultValue
    }

}
