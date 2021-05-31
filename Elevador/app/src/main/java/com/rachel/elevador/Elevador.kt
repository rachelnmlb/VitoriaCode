package com.rachel.elevador

class Elevador (
    val capacidadeMax: Int,
    val totalAndares: Int,
    ) {
    var qtdPessoas: Int = 0
        private set
    var andarAtual: Int =0
        private set

    fun irPara(andar: Int): Boolean{
        if (andar in 1 .. totalAndares){
            andarAtual = andar
            return true
        } else
            return false
    }
    fun entrar(): Boolean {
        if (qtdPessoas < capacidadeMax) {
            qtdPessoas++
            return true
        } else {
            return false
        }
    }

    fun sair(): Boolean {
        if (qtdPessoas > 0) {
            qtdPessoas --
            return true
        } else {
            return false
        }
    }

}
