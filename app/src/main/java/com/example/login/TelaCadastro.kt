package com.example.login

import android.app.AlertDialog
import android.content.ContentValues
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.login.databinding.ActivityTelaCadastroBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class TelaCadastro : AppCompatActivity() {
    //private lateinit var binding: ActivityTelaCadastroBinding
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //binding = ActivityTelaCadastroBinding.inflate(layoutInflater)
        //setContentView(binding.root)
        setContentView(R.layout.activity_tela_cadastro)
        auth = Firebase.auth


        var campoEmail: EditText = findViewById(R.id.editEmail)
        var campoSenha: EditText = findViewById(R.id.editSenha)
        var botaoCadastro: Button = findViewById<Button>(R.id.btnCadastrar)

        botaoCadastro.setOnClickListener {
            auth.createUserWithEmailAndPassword(campoEmail.text.toString(), campoSenha.text.toString())
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        Log.d(ContentValues.TAG, "createUserWithEmail:success")
                        val user = auth.currentUser
                        Toast.makeText(
                            baseContext,
                            "Authentication sucess: "+ user,
                            Toast.LENGTH_LONG,
                        ).show()
                        finish()
                    } else {
                        // If sign in fails, display a message to the user.
                        Log.w(ContentValues.TAG, "createUserWithEmail:failure", task.exception)
                        Toast.makeText(
                            baseContext,
                            "Authentication failed.",
                            Toast.LENGTH_SHORT,
                        ).show()
                    }
                }
        }


    }


}