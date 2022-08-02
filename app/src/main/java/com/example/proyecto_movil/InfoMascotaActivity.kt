package com.example.proyecto_movil

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import com.example.proyecto_movil.databinding.ActivityInfoMascotaBinding
import kotlinx.android.synthetic.main.activity_info_mascota.*

class InfoMascotaActivity : AppCompatActivity(){

    private lateinit var binding: ActivityInfoMascotaBinding
    private lateinit var listaDetalles : List<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInfoMascotaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        showItem()

    }

    private fun showItem() {


        listaDetalles = listOf(
            "Tipo: ------------",
            "Raza: ------------",
            "Sexo: ------------")

        val adapter = ArrayAdapter(this, R.layout.list_detalle,listaDetalles)
        binding.autoCompleteDetalle.setAdapter(adapter)
    }

}