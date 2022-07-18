package com.example.proyecto_movil

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView

class AnuncioAdapter(
    private val context: Activity,
    private val arrayList: List<Anuncio>
) :
    ArrayAdapter<Anuncio>(context, R.layout.activity_anuncio, arrayList) {


    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val inflater: LayoutInflater = LayoutInflater.from(context)
        val view: View = inflater.inflate(R.layout.list_anuncio, null)

        val titulo: TextView = view.findViewById(R.id.titulo)
        val contenido: TextView = view.findViewById(R.id.descA)

        titulo.text =arrayList[position].titulo
        contenido.text= arrayList[position].contenido

        return view

    }
}

