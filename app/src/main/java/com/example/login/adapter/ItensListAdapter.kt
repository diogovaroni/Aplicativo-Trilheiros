package com.example.login.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.login.model.Produto
import com.example.login.R

class ItensListAdapter(val listaProdutos: ArrayList<Produto>, val onClickListener: OnClickListener) :
    RecyclerView.Adapter <ItensListAdapter.ItensViewHolder>() {

    var contadorOncreate = 0
    var contadorOnBind = 0

    class ItensViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
            val textView: TextView = itemView.findViewById(R.id.text_modelo)
    }

    class OnClickListener(val clickListener: (produto: Produto) -> Unit){
        fun onClick(produto: Produto) = clickListener(produto)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItensViewHolder {
        contadorOncreate++
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.row_produtos_lista,parent,  false)

        return ItensViewHolder(view)
    }
 //8:57
    override fun getItemCount(): Int {
       return listaProdutos.size
    }

    override fun onBindViewHolder(holder: ItensViewHolder, position: Int) {
        contadorOnBind++
        val produto = listaProdutos[position]
        holder.textView.setText(produto.modelo)
        holder.itemView.setOnClickListener{
            onClickListener.onClick(produto)
        }
    }
}