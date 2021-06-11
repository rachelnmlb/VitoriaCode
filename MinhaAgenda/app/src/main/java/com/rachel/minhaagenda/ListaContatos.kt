package com.rachel.minhaagenda

class ListaContatos() {
    val contatos = mutableListOf<Contato>()

    fun adicionar(contato: Contato){
        contatos.add(contato)
    }

    fun pesquisar(nome: String): List<Contato> {
        return contatos.filter { contato -> contato.nome.contains(nome, ignoreCase = true) }
    }
    fun exibirLista(contatos: List<Contato> ) :String {
    var i = 1
    var listaString = ""

    val contatosOrdenados = contatos.sortedWith(
        compareBy(String.CASE_INSENSITIVE_ORDER, {it.nome})
    )

    for (contato in contatosOrdenados) {
        if (contato is ContatoPessoal) {
            listaString += "$i- Nome: ${contato.nome}\nTelefone: ${contato.numero}\nReferencia: ${contato.referencia} \n\n"
            i++
        } else if (contato is ContatoProfissional) {
            listaString += "$i- Nome: ${contato.nome}\nTelefone: ${contato.numero}\nE-mail: ${contato.email}\n\n"
            i++
        }
    }
    return listaString
}

}
