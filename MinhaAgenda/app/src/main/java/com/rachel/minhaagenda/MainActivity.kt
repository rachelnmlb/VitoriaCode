package com.rachel.minhaagenda

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.InputType
import android.util.Patterns
import android.view.View
import android.widget.RadioButton
import android.widget.Toast
import com.rachel.minhaagenda.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private val listaContatos = ListaContatos()
    lateinit var nome: String
    lateinit var numero: String
    lateinit var informacaoContato: String
    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.btnSalvar.setOnClickListener {

            nome = binding.nome.editText?.text.toString()
            numero = binding.numero.editText?.text.toString()
            informacaoContato = binding.informacao.editText?.text.toString()


            if (validar()) {

                if (binding.pessoal.isChecked){
                    var contatoPessoal = ContatoPessoal(nome,numero,informacaoContato)
                    listaContatos.adicionar(contatoPessoal)
                    limparErro()
                } else {
                    if (Patterns.EMAIL_ADDRESS.matcher(informacaoContato).matches()) {
                        var contatoTrabalho = ContatoProfissional(nome, numero, informacaoContato)
                        listaContatos.adicionar(contatoTrabalho)
                        limparErro()
                    } else {
                        binding.informacao.error = "E-mail inválido!"
                    }

                }
                binding.nome.editText?.text?.clear()
                binding.numero.editText?.text?.clear()
                binding.informacao.editText?.text?.clear()
            }

            exibirLista(listaContatos.contatos)
        }

        binding.btnPesquisar.setOnClickListener {
            binding.btnVoltar.visibility = View.VISIBLE
            var pesquisa = binding.pesquisar.editText?.text.toString()
            exibirLista(listaContatos.pesquisar(pesquisa))
        }

        binding.btnVoltar.setOnClickListener {
            exibirLista(listaContatos.contatos)
            binding.btnVoltar.visibility = View.INVISIBLE
            binding.pesquisar.editText?.text?.clear()
        }

    }

    fun validar() : Boolean{
        var validade = true

        if (nome.isEmpty()){
            binding.nome.error = getString(R.string.error)
            validade = false
        }
        if (numero.isEmpty()){
            binding.numero.error = getString(R.string.error)
            validade = false
        }
        if (informacaoContato.isEmpty()){
            binding.informacao.error = getString(R.string.error)
            validade = false
        }
        return validade
    }

    fun limparErro(){
        binding.nome.error = null
        binding.numero.error = null
        binding.informacao.error = null

    }

    fun onRadioButtonClicked(view: View) {
        if (view is RadioButton) {
            val checked = view.isChecked

            when (view.getId()) {
                binding.pessoal.id ->
                    if (checked) {
                        binding.informacao.editText?.inputType = InputType.TYPE_CLASS_TEXT
                        binding.informacao.hint = "Referência"
                    }
                binding.trabalho.id ->
                    if (checked) {
                        binding.informacao.editText?.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_WEB_EMAIL_ADDRESS
                        binding.informacao.hint = "E-mail"
                    }
            }
        }
    }

    private fun exibirLista(contatos: List<Contato> ) {
        var i = 1
        var listaString = ""

        val contatosOrdenados = contatos.sortedWith(
                compareBy(String.CASE_INSENSITIVE_ORDER, {it.nome})
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
        binding.listaContatos.text = listaString
    }
}

