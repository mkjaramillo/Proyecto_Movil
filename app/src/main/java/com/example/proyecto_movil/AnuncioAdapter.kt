package com.example.proyecto_movil

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.example.proyecto_movil.Anuncio
import com.example.proyecto_movil.R
import kotlinx.android.synthetic.main.item_anuncio.view.*

class AnuncioAdapter(private val nContext: Context, private val listaAnuncio: List<Anuncio>) :
    ArrayAdapter<Anuncio>(nContext, 0, listaAnuncio) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val layout = LayoutInflater.from(nContext).inflate(R.layout.item_anuncio,parent,false)

        val anuncio = listaAnuncio[position]

        layout.tituloAnuncio.text = anuncio.titulo
        layout.descripAnuncio.text = anuncio.contenido

        return layout
    }

}