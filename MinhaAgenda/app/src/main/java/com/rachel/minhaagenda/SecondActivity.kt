package com.rachel.minhaagenda

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.rachel.minhaagenda.databinding.ActivitySecondBinding
import kotlinx.android.synthetic.main.activity_second.*

class SecondActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivitySecondBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val lista = intent.extras?.get(MainActivity.LISTA) as List<Contato>
        binding.listaContatos.text = exibirLista(lista)
        Log.d("lista contato", exibirLista(lista))

        binding.btnVoltar.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)

        }

        binding.btnPesquisar.setOnClickListener {

            var pesquisa = binding.pesquisar.editText?.text.toString()
            binding.listaContatos.text= exibirLista(pesquisar(pesquisa,lista))
        }



    }

    fun exibirLista(contatos: List<Contato>): String {
        var i = 1
        var listaString = ""

        val contatosOrdenados = contatos.sortedWith(
            compareBy(String.CASE_INSENSITIVE_ORDER, { it.nome })
        )

        for (contato in contatosOrdenados) {
            if (contato is ContatoPessoal) {
                listaString += "$i- Nome: ${contato.nome}\nTelefone: ${contato.numero}\nReferencia: ${contato.referencia} \n\n"
                i++
            } else if (contato is ContatoProfissional) {
                listaString += "$i- Nome: ${contato.nome}\nTelefone: ${contato.numero}\nE-mail: ${contato.email}\n\n"
                i++
            }
        }
        return listaString
    }

    fun pesquisar(nome: String, contatos: List<Contato>): List<Contato> {
        return contatos.filter { contato -> contato.nome.contains(nome, ignoreCase = true) }
    }
}
