package com.rachel.calculandodatas

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.DatePicker
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.time.LocalDate
import java.time.temporal.ChronoUnit
import java.util.*

class MainActivity : AppCompatActivity() {
    lateinit var inputNome: EditText
    lateinit var btn : Button
    lateinit var txtMesagem : TextView
    lateinit var inputDataNascimento : DatePicker
    lateinit var inputPresente : EditText
    val listaPresentes = mutableSetOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        inputNome = findViewById(R.id.txtNome)
        btn = findViewById(R.id.btn)
        txtMesagem = findViewById(R.id.txtMensagem)
        inputDataNascimento = findViewById(R.id.dataNascimento)
        inputPresente = findViewById(R.id.txtPresente)

        inputDataNascimento.maxDate = Date().time

        btn.setOnClickListener{

            var nome = inputNome?.text.toString()
            var dataNascimento = LocalDate.of(inputDataNascimento.year, inputDataNascimento.month + 1, inputDataNascimento.dayOfMonth)
            var presente = inputPresente?.text.toString()
            var intervaloDias = calcularDias(dataNascimento)
            var mensagem : String
            if (nome.isEmpty()) nome = "Desconhecido"
            if (presente.isEmpty()) presente = "Nada"

            mensagem = "Olá $nome, faltam $intervaloDias dias para o seu aniversário! Espero que você ganhe um $presente!"

            cadastrarPresente(mensagem)
        }
    }

    fun calcularDias(dataNascimento : LocalDate ) : Long {
        var dataHoje = LocalDate.now()
        var proxAniversario = dataNascimento.withYear(dataHoje.year)

        if (proxAniversario.isBefore(dataHoje)){
            proxAniversario = proxAniversario.plusYears(1)
        }
        return ChronoUnit.DAYS.between(dataHoje, proxAniversario)
    }

    fun cadastrarPresente( mensagem : String) {

        txtMesagem.visibility = View.VISIBLE
        listaPresentes.add(mensagem)

        var elemento = ""
        var i = 1

        for(item in listaPresentes) {
            elemento += "$i- $item \n"
            i++
        }

        txtMesagem.text = elemento
    }
}