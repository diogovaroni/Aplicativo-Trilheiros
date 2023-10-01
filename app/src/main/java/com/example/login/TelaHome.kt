package com.example.login

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class TelaHome : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tela_home)

        supportActionBar?.hide()
        window.statusBarColor = Color.parseColor("#05685F")
    }
}