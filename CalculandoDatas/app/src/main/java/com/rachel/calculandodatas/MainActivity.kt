package com.rachel.calculandodatas

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.DatePicker
import android.widget.EditText
import android.widget.TextView
import java.time.Instant
import java.time.LocalDate
import java.time.Period
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit
import java.time.temporal.TemporalUnit

class MainActivity : AppCompatActivity() {
    lateinit var inputNome: EditText
    lateinit var btn : Button
    lateinit var txtMesagem : TextView
    lateinit var inputDataNascimento : DatePicker
    lateinit var inputPresente : EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        inputNome = findViewById(R.id.txtNome)
        btn = findViewById(R.id.btn)
        txtMesagem = findViewById(R.id.txtMensagem)
        inputDataNascimento = findViewById(R.id.dataNascimento)
        inputPresente = findViewById(R.id.txtPresente)

        btn.setOnClickListener{

            var nome = inputNome?.text.toString()
            var dataNascimento = LocalDate.of(inputDataNascimento.year, inputDataNascimento.month + 1, inputDataNascimento.dayOfMonth)
            var presente = inputPresente?.text.toString()
            var intervaloDias = calcularDias(dataNascimento)

            if (nome.isEmpty()) nome = "Desconhecido"
            if (presente.isEmpty()) presente = "Nada"

            txtMesagem.text =
                    "Olá $nome, faltam $intervaloDias dias para o seu aniversário! Espero que você ganhe um $presente!"

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
}