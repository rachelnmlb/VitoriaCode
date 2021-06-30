package com.rachel.minhaagenda

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.rachel.minhaagenda.databinding.ActivitySecondBinding
import kotlinx.android.synthetic.main.activity_second.*
import java.util.*
import kotlin.collections.ArrayList

class SecondActivity : AppCompatActivity() {
    private lateinit var rvContatos :RecyclerView
    private lateinit var contatosAdapter: ContatosAdapter
    private lateinit var lista: MutableList<Contato>
    private lateinit var dataSet: MutableList<Contato>

    private val binding by lazy {
        ActivitySecondBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        lista = intent.extras?.get(MainActivity.LISTA) as MutableList<Contato>
        dataSet = ordenarLista()

        rvContatos = findViewById(R.id.rvContatos)
        contatosAdapter = ContatosAdapter(context = this, dataSet = dataSet)
        rvContatos.adapter = contatosAdapter
        rvContatos.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        binding.btnVoltar.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)

        }

        binding.btnPesquisar.setOnClickListener {
            var pesquisa = binding.pesquisar.editText?.text.toString()
            val resultado = pesquisar(pesquisa)
            atualizarDataSet(resultado)

        }
    }

    private fun ordenarLista(): MutableList<Contato> {
        return lista.sortedWith(
            compareBy(String.CASE_INSENSITIVE_ORDER, { it.nome })
        ).toMutableList()
    }

    private fun pesquisar(nome: String): MutableList<Contato> {
        return ordenarLista().filter { contato -> contato.nome.contains(nome, ignoreCase = true) }.toMutableList()
    }

    private fun atualizarDataSet(novoDataSet: List<Contato>) {
        dataSet.clear()
        dataSet.addAll(novoDataSet)
        contatosAdapter.notifyDataSetChanged()
    }
}
