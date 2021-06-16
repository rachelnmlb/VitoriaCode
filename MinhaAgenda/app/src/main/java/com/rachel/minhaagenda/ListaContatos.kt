package com.rachel.minhaagenda

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
open class ListaContatos() : Parcelable {
    open val contatos = ArrayList<Contato>()


    fun adicionar(contato: Contato) {
        contatos.add(contato)
    }


}

