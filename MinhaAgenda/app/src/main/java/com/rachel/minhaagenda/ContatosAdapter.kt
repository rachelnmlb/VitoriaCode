package com.rachel.minhaagenda

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import android.view.ViewGroup
import android.widget.TextView

class ContatosAdapter(val context: Context, val dataSet: List<Contato>) : RecyclerView.Adapter<ContatosAdapter.ContatosViewHolder>() {

    class ContatosViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val nome = view.findViewById<TextView>(R.id.itemNome)
        val descricao = view.findViewById<TextView>(R.id.itemDescricao)
        val numero = view.findViewById<TextView>(R.id.itemNumero)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContatosViewHolder {
        val instanciaView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_contato, parent, false)
        return ContatosViewHolder(instanciaView)
    }

    override fun onBindViewHolder(holder: ContatosViewHolder, position: Int) {
        holder.nome.text = dataSet[position].nome
        holder.numero.text = dataSet[position].numero
        holder.descricao.text = dataSet[position].descricao

    }

    override fun getItemCount(): Int = dataSet.size

}