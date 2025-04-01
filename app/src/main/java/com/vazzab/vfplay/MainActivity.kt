package com.vazzab.vfplay

import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.ComponentActivity

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val buttons = listOf(
            R.id.button1 to R.string.navigation_button_main,
            R.id.button2 to R.string.navigation_button_category,
            R.id.button3 to R.string.navigation_button_bookmark,
            R.id.button4 to R.string.navigation_button_new,
            R.id.button5 to R.string.navigation_button_top
        )

        buttons.forEach { (buttonId, stringId) ->
            findViewById<Button>(buttonId).setOnClickListener {
                Toast.makeText(this, getString(stringId), Toast.LENGTH_SHORT).show()
            }
        }

    }
}