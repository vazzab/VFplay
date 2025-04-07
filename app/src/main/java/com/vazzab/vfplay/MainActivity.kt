package com.vazzab.vfplay

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.google.android.material.bottomnavigation.BottomNavigationView


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment_container) as? NavHostFragment
        if (navHostFragment != null) {
            val navController = navHostFragment.navController
            val bottomNavView = findViewById<BottomNavigationView>(R.id.bottom_nav_view)
            NavigationUI.setupWithNavController(bottomNavView, navController)
        } else {
            Log.e("MainActivity", "NavHostFragment is null. Check activity_main.xml")
        }

    }
}