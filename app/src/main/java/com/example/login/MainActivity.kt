package com.example.login

import android.content.ContentValues
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.login.databinding.ActivityMainBinding
import com.example.login.views.TelaHome
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var auth: FirebaseAuth
    override fun onStart() {

        super.onStart()
        auth = Firebase.auth
        val currentUser = auth.currentUser
        if (currentUser != null) {
            //fluxo usuario logado
            startActivity(Intent(this,TelaHome::class.java))

        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        // Initialize Firebase Auth
        auth = Firebase.auth

        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = auth.currentUser
        if (currentUser != null) {
            //fluxo usuario logado
            startActivity(Intent(this,TelaHome::class.java))

        }else{
            // fluxo usuario nao logado
            var botaoEntrar: Button = findViewById<Button>(R.id.btEntrar)
            botaoEntrar.setOnClickListener {
                var campoEmail: EditText = findViewById(R.id.editEmail)
                var campoSenha: EditText = findViewById(R.id.editSenha)
                auth.signInWithEmailAndPassword(campoEmail.text.toString(), campoSenha.text.toString())
                    .addOnCompleteListener(this) { task ->
                        if (task.isSuccessful) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(ContentValues.TAG, "signInWithEmail:success")
                            val user = auth.currentUser
                            startActivity(Intent(this,TelaHome::class.java))

                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(ContentValues.TAG, "signInWithEmail:failure", task.exception)
                            Toast.makeText(
                                baseContext,
                                "Authentication failed.",
                                Toast.LENGTH_SHORT,
                            ).show()
                            //updateUI(null)
                        }
                    }
            }
        }
        binding.txtTelaCadastro.setOnClickListener{
            val cadastro = Intent(this, TelaCadastro::class.java)

            startActivity(cadastro)
        }


        supportActionBar?.hide()
        window.statusBarColor = Color.parseColor("#19B1A3")

    }




}