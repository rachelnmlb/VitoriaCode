package com.rachel.minhaagenda

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.InputType
import android.view.View
import android.widget.RadioButton
import com.rachel.minhaagenda.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private val listaContatos = ListaContatos()
    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.btnSalvar.setOnClickListener {
            if (binding.pessoal.isChecked){
                var contatoPessoal = ContatoPessoal(binding.Nome.editText.toString(),
                                                    binding.Numero.editText.toString(),
                                                    binding.informacao.editText.toString())
                listaContatos.adicionar(contatoPessoal)
            } else {
                var contatoTrabalho = ContatoProfissional(binding.Nome.editText.toString(),
                                                          binding.Numero.editText.toString(),
                                                          binding.informacao.editText.toString())
                listaContatos.adicionar(contatoTrabalho)
            }
            binding.listaContatos.text = listaContatos.ordenarLista().toString()
        }


    }
    fun onRadioButtonClicked(view: View) {
        if (view is RadioButton) {
            // Is the button now checked?
            val checked = view.isChecked

            // Check which radio button was clicked
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
}

