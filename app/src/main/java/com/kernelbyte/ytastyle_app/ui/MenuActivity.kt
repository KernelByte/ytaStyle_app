package com.kernelbyte.ytastyle_app.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.Menu
import android.widget.ImageView
import android.widget.TextView
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.navigation.NavigationView
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.kernelbyte.ytastyle_app.R
import com.kernelbyte.ytastyle_app.databinding.ActivityMenuBinding
import com.kernelbyte.ytastyle_app.util.PreferenceHelper

class MenuActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMenuBinding
    private lateinit var dialog: BottomSheetDialog
    private lateinit var userPreferences: PreferenceHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)
        userPreferences = PreferenceHelper(this)
        setSupportActionBar(binding.appBarMenu.toolbar)

        binding.appBarMenu.fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
        val drawerLayout: DrawerLayout = binding.drawerLayout
        val navView: NavigationView = binding.navView
        val navController = findNavController(R.id.nav_host_fragment_content_menu)

        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val header = navView.getHeaderView(0)
        var nombreView = header.findViewById<TextView>(R.id.tv_nombre_user)
        var correoView = header.findViewById<TextView>(R.id.tv_correo_user)
        val imagenUserView = header.findViewById<ImageView>(R.id.iv_user_acount)

        nombreView.text = userPreferences.getString("nombre","")
        correoView.text = userPreferences.getString("correo","")


        imagenUserView.setOnClickListener{
            showBottomSheet()
        }

        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_venta, R.id.nav_producto, R.id.nav_cliente
            ), drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_menu)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    @SuppressLint("InflateParams")
    private fun showBottomSheet(){
        val dialogView = layoutInflater.inflate(R.layout.buttom_sheet,null)
        dialog = BottomSheetDialog(this,R.style.BottomSheetDialogTheme)
        dialog.setContentView(dialogView)
        dialog.show()
    }
}