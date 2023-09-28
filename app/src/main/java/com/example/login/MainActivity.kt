package com.example.login

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.login.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()
        window.statusBarColor = Color.parseColor("#FFFFFF")

        binding.btEntrar.setOnClickListener {
            val email = binding.editEmail.text.toString()
            val senha = binding.editSenha.text.toString()

            when {
                email.isEmpty() -> {
                    binding.editEmail.error = "Preencha o E-mail!"
                }
                senha.isEmpty() -> {
                    binding.editSenha.error = "Preencha a Senha!"
                }
            }
        }
    }
}