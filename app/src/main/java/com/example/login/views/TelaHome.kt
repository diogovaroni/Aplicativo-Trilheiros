package com.example.login.views

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.login.adapter.ItensListAdapter
import com.example.login.data.ProdutoTeste
import com.example.login.databinding.TelaHomeBinding
import com.example.login.model.Produto

class TelaHome : AppCompatActivity() {

    private lateinit var  binding: TelaHomeBinding
    private lateinit var  adapter: ItensListAdapter
    private lateinit var  teste: ProdutoTeste
    private var pos = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding =  TelaHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.recyclerView.layoutManager = LinearLayoutManager(this)

        teste = ProdutoTeste()
        adapter = ItensListAdapter(teste.listaProdutos, ItensListAdapter.OnClickListener { produto ->
            pos = pesquisaPosicao(produto.id)
            binding.editModelo.setText(teste.listaProdutos[pos].modelo)
        })

        binding.recyclerView.adapter = adapter

        binding.buttonInserir.setOnClickListener {
            val modelo = binding.editModelo.text.toString()
            if (modelo.isNotEmpty()) {
                val novoProduto = Produto(teste.listaProdutos.size + 1, modelo)
                teste.listaProdutos.add(novoProduto)
                adapter.notifyDataSetChanged()
            }
        }

        binding.buttonEditar.setOnClickListener {
            if (pos >= 0) {
                val novoModelo = binding.editModelo.text.toString()
                if (novoModelo.isNotEmpty()) {
                    teste.listaProdutos[pos].modelo = novoModelo
                    adapter.notifyDataSetChanged()
                    pos = -1
                }
            }
        }

        binding.buttonExcluir.setOnClickListener {
            if (pos >= 0) {
                teste.listaProdutos.removeAt(pos)
                adapter.notifyDataSetChanged()
                pos = -1
            }
        }
    }

    private fun pesquisaPosicao(id: Int): Int {
        for (i in teste.listaProdutos.indices) {
            if (teste.listaProdutos[i].id == id) {
                return i
            }
        }
        return -1
    }
}
