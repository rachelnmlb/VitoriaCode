package com.rachel.primeiroappemandroid

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    lateinit var nome: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var idade = 15 + 16
        nome = findViewById<TextView>(R.id.piramide)
        nome.text = "Olá! Eu sou Rachel e tenho $idade anos!"

    }

}
