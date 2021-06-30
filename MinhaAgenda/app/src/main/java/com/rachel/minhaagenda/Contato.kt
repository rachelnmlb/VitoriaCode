package com.rachel.minhaagenda
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
open class Contato(
    open var nome: String,
    open var numero: String,
    val descricao: CharSequence
): Parcelable{
}
