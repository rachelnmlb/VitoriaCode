package com.rachel.minhaagenda

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.InputType
import android.util.Log
import android.util.Patterns
import android.view.View
import android.widget.RadioButton
import android.widget.Toast
import com.rachel.minhaagenda.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
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

            val intent = Intent(this, SecondActivity::class.java)
            intent.putExtra(LISTA, listaContatos.contatos)
            startActivity(intent)
        }


    }

    fun validar() : Boolean{
        var validade = true

        if (nome.isEmpty()){
            binding.nome.error = getString(R.string.erro)
            validade = false
        }
        if (numero.isEmpty()){
            binding.numero.error = getString(R.string.erro)
            validade = false
        }
        if (informacaoContato.isEmpty()){
            binding.informacao.error = getString(R.string.erro)
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
    companion object{
        val listaContatos = ListaContatos()
        val LISTA = "ListaContato"
    }

}

