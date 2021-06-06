package com.rachel.elevador

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.textfield.TextInputLayout

class MainActivity : AppCompatActivity() {

    lateinit var inputAndarDigitado : TextInputLayout
    lateinit var andarAtual : TextView
    lateinit var qtdPessoas : TextView
    lateinit var btnSolicitarAndar : Button
    lateinit var btnEntrar : Button
    lateinit var btnSair : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val elevador = Elevador(5,8)
        inputAndarDigitado = findViewById(R.id.andarDigitado)
        andarAtual = findViewById(R.id.andarAtual)
        btnSolicitarAndar = findViewById(R.id.btnSolicitarAndar)
        btnEntrar = findViewById(R.id.btnEntrar)
        btnSair = findViewById(R.id.btnSair)
        qtdPessoas = findViewById(R.id.qtdPessoas)

        qtdPessoas.text = "0/${elevador.capacidadeMax}"
        andarAtual.text = "T"

        btnSolicitarAndar.setOnClickListener {

            var andarDesejado = inputAndarDigitado?.editText?.text.toString().toInt()

            if (elevador.irPara(andarDesejado)) {
                if (elevador.andarAtual == 0){
                    andarAtual.text = "Terreo"
                } else {
                    andarAtual.text = " ${elevador.andarAtual}° \n andar"
                }
            } else {
                Toast.makeText(this,
                    "O andar escolhido não é válido, escolha outro andar.",
                    Toast.LENGTH_SHORT).show()
            }

        }

        btnEntrar.setOnClickListener {

                if (elevador.entrar()){
                    qtdPessoas.text = "${elevador.qtdPessoas}/${elevador.capacidadeMax}"
                } else {
                    Toast.makeText(this,
                        "O elevador está cheio! Espere alguem sair.",
                        Toast.LENGTH_SHORT).show()
                }
        }

        btnSair.setOnClickListener {

                if (elevador.sair()){
                    qtdPessoas.text="${elevador.qtdPessoas}/${elevador.capacidadeMax}"
                } else {
                    Toast.makeText(this,
                        "Não tem ninguém para sair do elevador.",
                        Toast.LENGTH_SHORT).show()
                }
        }

        
    }
}