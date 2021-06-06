package com.rachel.minhaagenda

class ListaContatos() {
    val listaContatos = mutableListOf<Contato>()

    fun adicionar(contato: Contato){
        listaContatos.add(contato)
    }

    fun pesquisar(nome: String): List<Contato> {
        return listaContatos.filter { contato -> contato.nome.contains(nome)}
    }

    fun ordenarLista (): List<Contato> {
        return listaContatos.sortedBy { contato -> contato.nome }
    }
}