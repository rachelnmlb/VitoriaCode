package com.rachel.minhaagenda

import android.util.Patterns
import kotlinx.android.parcel.Parcelize

@Parcelize
class ContatoProfissional(override var nome: String, override var numero: String, var email: String): Contato(nome, numero){
}
