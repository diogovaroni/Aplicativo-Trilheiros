package com.example.login

import android.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import com.example.login.databinding.ActivityTelaCadastroBinding

class TelaCadastro : AppCompatActivity() {
    private lateinit var binding: ActivityTelaCadastroBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTelaCadastroBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Adicione um TextWatcher aos campos de senha e confirmar senha
        binding.editSenha.addTextChangedListener(textWatcher)
        binding.editConfirmarSenha.addTextChangedListener(textWatcher)
    }
    private val textWatcher = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        }

        override fun afterTextChanged(s: Editable?) {
            // Verifique se as senhas coincidem
            val senha = binding.editSenha.text.toString()
            val confirmarSenha = binding.editConfirmarSenha.text.toString()

            if (senha == confirmarSenha) {
                // Senhas coincidem, oculte a mensagem de erro
                binding.txtMensagemCadastro.visibility = View.GONE
            } else {
                // Senhas não coincidem, mostre a mensagem de erro
                binding.txtMensagemCadastro.visibility = View.VISIBLE
                binding.txtMensagemCadastro.text = "A senha não corresponde à digitada anteriormente"
            }

            // Botão de cadastro
            binding.btnCadastrar.setOnClickListener {
                // Verifique se todos os campos estão preenchidos
                if (todosCamposPreenchidos()) {
                    // Simule uma verificação de sucesso do cadastro
                    if (realizarCadastro()) {
                        // Cadastro bem-sucedido, exiba a mensagem
                        binding.txtMensagemCadastro.visibility = View.VISIBLE
                        binding.txtMensagemCadastro.text = "Cadastrado com sucesso!"
                    }
                } else {
                    // Mostrar um alerta para preencher todos os campos
                    exibirAlertaPreencherCampos()
                }
            }
        }

        // Simulação de cadastro bem-sucedido
        private fun realizarCadastro(): Boolean {
            // Coloque sua lógica real de cadastro aqui
            // Retorne true se o cadastro for bem-sucedido, caso contrário, retorne false
            return true
        }
    }

    private fun todosCamposPreenchidos(): Boolean {
        val campoNome = binding.editNome.text.toString()
        val campoEmail = binding.editEmail.text.toString()
        val campoSenha = binding.editSenha.text.toString()
        val campoConfirmarSenha = binding.editConfirmarSenha.text.toString()

        return campoNome.isNotEmpty() && campoEmail.isNotEmpty() &&
                campoSenha.isNotEmpty() && campoConfirmarSenha.isNotEmpty()
    }

    private fun exibirAlertaPreencherCampos() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Alerta")
        builder.setMessage("Por favor, preencha todos os campos antes de cadastrar.")
        builder.setPositiveButton("OK") { dialog, _ -> dialog.dismiss() }
        val alertDialog = builder.create()
        alertDialog.show()
    }
}