package com.example.proyecto_movil

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ListView
import android.widget.TextView
import kotlinx.android.synthetic.main.fragment_anunciar.view.*

class AnunciarFragment : Fragment() {

    private lateinit var listaAnuncios: List<Anuncio>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val layout = inflater.inflate(R.layout.fragment_anunciar, container, false)

        val anuncio = Anuncio("Animal Perdido", "Si tu animal de compañia se ha perdido o escapado")
        val anuncio2 = Anuncio("Animal Encontrado", "Si has visto o encontrado a un animal perdido")
        val anuncio3 = Anuncio("Animal en Adopción", "Si necesitas que adopten a tu animal de compañia")

        listaAnuncios = listOf(anuncio, anuncio2, anuncio3)
        val adapter = AnuncioAdapter(requireActivity(), listaAnuncios)

        layout.listaa.adapter = adapter

/*
        layout.listaa.setOnItemClickListener { adapterView, view, i, l ->
            val intent = Intent(activity!!, MascotaActivity::class.java)
            val bundle= intent.extras
            val userMail = bundle?.getString("email")
            intent.putExtra("tipoAnuncio", listaAnuncios[i])
            intent.putExtra("email", userMail)
            startActivity(intent)
        }*/

        return layout
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        if (arguments != null) {
            val userMail = requireArguments().getString("email")
            val et: TextView = requireView().findViewById(R.id.tituloA)
            et.setText(userMail.toString())

            val lt: ListView = requireView().findViewById(R.id.listaa)

            lt.setOnItemClickListener { adapterView, view, i, l ->
                /* val intent = Intent(activity!!, MascotaActivity::class.java)
                 val bundle = intent.extras
                 val userMail = bundle?.getString("email")
                 intent.putExtra("tipoAnuncio", listaAnuncios[i])
                 intent.putExtra("mmmail", userMail)
                 startActivity(intent)*/

                startActivity(Intent(requireActivity(), MascotaActivity::class.java).apply {
                    putExtra("tipoAnuncio", listaAnuncios[i])
                    putExtra("mail", userMail)
                   // putExtra("direccion", binding.)
                })
                //lt.setText(userMail.toString())
            }


        }

    }
}