
package com.example.proyecto_movil.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.proyecto_movil.R
import com.example.proyecto_movil.databinding.EstiloListaBinding
import com.example.proyecto_movil.model.Publicaciones
import com.squareup.picasso.Picasso

class AdapterPublicacion(lista: List<Publicaciones>, val onClickItemSelected: (Publicaciones) -> Unit): RecyclerView.Adapter<AdapterPublicacion.PublicacionViewHolder>() {
    private var publicacionList: MutableList<Publicaciones> = lista.toMutableList()

    inner class PublicacionViewHolder(item: View) :
        RecyclerView.ViewHolder(item) {
        private var binding: EstiloListaBinding = EstiloListaBinding.bind(item)

        fun render(item: Publicaciones, itemClick: (Publicaciones) -> Unit) {
            Picasso.get().load(item.imagen).into(binding.imageView)
            binding.imageView
            binding.idTipo.text = item.tipo.toString()
            binding.idTitulo.text = item.titulo.toString()
            binding.idTiempo.text=item.tiempo.toString()
            itemView.setOnClickListener() {
                itemClick(item)
            }
        }

    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): AdapterPublicacion.PublicacionViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return PublicacionViewHolder(inflater.inflate(R.layout.estilo_lista, parent, false))
    }

    override fun onBindViewHolder(holder: AdapterPublicacion.PublicacionViewHolder, position: Int) {
        holder.render(publicacionList[position], onClickItemSelected)
    }

    override fun getItemCount(): Int =publicacionList.size
}