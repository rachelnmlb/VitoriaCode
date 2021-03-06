package com.rachel.primeiroappemandroid

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    lateinit var nome: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }

        override fun onStart() {
            super.onStart()
            Log.d("MeuAplicativo", "Ciclo de vida onStart")
        }

        override fun onResume() {
            super.onResume()
            Log.d("MeuAplicativo", "Ciclo de vida onResumo")
        }

        override fun onPause() {
            super.onPause()
            Log.d("MeuAplicativo", "Ciclo de vida onPause")
            Toast.makeText(this, "Lembre de preencher os dados restantes mais tarde!", Toast.LENGTH_SHORT).show()
        }

        override fun onDestroy() {
            super.onDestroy()
            Log.d("MeuAplicativo", "Ciclo de vida onDestroy")
        }

    }



