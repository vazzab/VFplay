package com.vazzab.vfplay

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Switch
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.widget.Toolbar
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolbar = findViewById<Toolbar>(R.id.top_toolbar)
        setSupportActionBar(toolbar)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment_container) as? NavHostFragment
        if (navHostFragment != null) {
            val navController = navHostFragment.navController
            val bottomNavView = findViewById<BottomNavigationView>(R.id.bottom_nav_view)
            NavigationUI.setupWithNavController(bottomNavView, navController)
        } else {
            Log.e("MainActivity", "NavHostFragment is null. Check activity_main.xml")
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.top_app_bar_menu, menu)
        return true
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_search -> {
                Toast.makeText(this, "Поиск нажат", Toast.LENGTH_SHORT).show()
                true
            }

            R.id.action_settings -> {
                showThemeDialog()
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }


    // Меню настроек
    private fun showThemeDialog() {
        val dialogView = layoutInflater.inflate(R.layout.theme_switch_dialog, null)
        val themeSwitch = dialogView.findViewById<Switch>(R.id.theme_switch)

        // Устанавливаем текущее состояние
        themeSwitch.isChecked = AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES

        themeSwitch.setOnCheckedChangeListener { _, isChecked ->
            AppCompatDelegate.setDefaultNightMode(
                if (isChecked) AppCompatDelegate.MODE_NIGHT_YES
                else AppCompatDelegate.MODE_NIGHT_NO
            )
        }

        AlertDialog.Builder(this)
            .setTitle("Настройки")
            .setView(dialogView)
            .setPositiveButton("ОК", null)
            .show()
    }
}
