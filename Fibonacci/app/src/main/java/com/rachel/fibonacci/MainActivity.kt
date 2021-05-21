package com.rachel.fibonacci

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    lateinit var inputIndice : EditText
    lateinit var btn : Button
    lateinit var mensagem : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        inputIndice = findViewById(R.id.indice)
        btn = findViewById(R.id.btn)
        mensagem = findViewById(R.id.resultado)

        btn.setOnClickListener{

            var indice = inputIndice?.text.toString()

            if (indice.isEmpty()) {
                inputIndice.error = "Informe um valor"
            } else {
                mensagem.text = calcularFibonacci(indice.toInt()).toString()
                mensagem.visibility = View.VISIBLE
            }
        }
    }

    fun calcularFibonacci (valor : Int) : List<Int> {

        val sequenciaFibonacci = mutableListOf<Int>()
        var posicao = valor - 1
        var anterior = 0
        var atual = 1

        for (i in 0..posicao) {

            if (i == 0 ){
                sequenciaFibonacci.add(0)
            } else if (i== 1){
                sequenciaFibonacci.add(1)
            }else {
                var soma = atual + anterior
                sequenciaFibonacci.add(soma)
                anterior = atual
                atual = soma
            }
        }
        return sequenciaFibonacci
    }
}