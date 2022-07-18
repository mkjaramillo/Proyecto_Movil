package com.example.proyecto_movil

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView

class DetalleAdapter(
    private val context: Activity,
    private val arrayList: List<Detalle>
) :
    ArrayAdapter<Detalle>(context, R.layout.activity_info_mascota, arrayList) {


    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val inflater: LayoutInflater = LayoutInflater.from(context)
        val view: View = inflater.inflate(R.layout.list_detalle, null)

        val titulo: TextView = view.findViewById(R.id.textOpc)
        val contenido: TextView = view.findViewById(R.id.textDescrp)

        titulo.text =arrayList[position].titulo
        contenido.text= arrayList[position].contenido

        return view

    }
}

