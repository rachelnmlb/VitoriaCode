package com.rachel.minhaagenda

import kotlinx.android.parcel.Parcelize

@Parcelize
class ContatoPessoal( override var nome: String, override var numero: String, var referencia: String ) : Contato( nome , numero){
}
