package com.example.login

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import com.example.login.databinding.ActivityMainBinding
import com.example.login.views.TelaHome
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()
        window.statusBarColor = Color.parseColor("#19B1A3")

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
                !email.contains("@gmail.com") -> {
                    val snackbar = Snackbar.make(it, "E-mail inválido!", Snackbar.LENGTH_SHORT)
                    snackbar.show()
                }
                senha.length <= 5 -> {
                    val snackbar = Snackbar.make(
                        it,
                        "A senha precisa ter pelo menos 6 caracteres!",
                        Snackbar.LENGTH_SHORT
                    )
                    snackbar.show()
                }
                else -> {
                    login(it)
                }
            }
        }
        binding.txtTelaCadastro.setOnClickListener{
            val cadastro = Intent(this, TelaCadastro::class.java)
            startActivity(cadastro)
        }
    }
    private fun login(view: View){
        val progressbar = binding.progressBar
        progressbar.visibility = View.VISIBLE

        binding.btEntrar.isEnabled = false
        binding.btEntrar.setTextColor(Color.parseColor("#FFFFFF"))

        Handler(Looper.getMainLooper()).postDelayed({
            navegarTelaHome()
            val snackbar = Snackbar.make(view,"Login efetuado com sucesso!",Snackbar.LENGTH_SHORT)
            snackbar.show()
        }, 3000)
    }
    private fun navegarTelaHome() {
        val intent = Intent(this, TelaHome::class.java)
        startActivity(intent)
    }
    override fun onRestart() {
        super.onRestart()
        binding.editEmail.setText("")
        binding.editSenha.setText("")
    }
}