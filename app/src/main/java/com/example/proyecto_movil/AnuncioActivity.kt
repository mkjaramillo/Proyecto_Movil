package com.example.proyecto_movil

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.proyecto_movil.databinding.ActivityAnuncioBinding

class AnuncioActivity: AppCompatActivity() {

    private lateinit var binding: ActivityAnuncioBinding
    private lateinit var listaAnuncios : List<Anuncio>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAnuncioBinding.inflate(layoutInflater)
        setContentView(binding.root)
//probando commit con mile
        ShowInitItem()

    }

    private fun ShowInitItem() {

        val anuncio= Anuncio("Animal Perdido", "Si tu animal de compañia se ha perdido o escapado")
        val anuncio2= Anuncio("Animal Encontrado", "Si has visto o encontrado a un animal perdido")
        val anuncio3= Anuncio("Animal en Adopción", "Si necesitas que adopten a tu animal de compañia")


        listaAnuncios = listOf(anuncio, anuncio2,anuncio3)
        //listaAnuncios.add(anuncio)
        //listaAnuncios.add(anuncio2)
        //listaAnuncios.add(anuncio3)

        binding.listaAnuncio.isClickable= true
        binding.listaAnuncio.adapter = AnuncioAdapter(this,listaAnuncios)

    }
}