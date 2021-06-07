package com.rachel.minhaagenda

class ListaContatos() {
    val contatos = mutableListOf<Contato>()

    fun adicionar(contato: Contato){
        contatos.add(contato)
    }

    fun pesquisar(nome: String): List<Contato> {
        return contatos.filter { contato -> contato.nome.contains(nome, ignoreCase = true) }
    }
}