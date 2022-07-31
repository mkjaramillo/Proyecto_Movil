package com.example.proyecto_movil.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.proyecto_movil.databinding.ActivityPublicacioCompletaBinding
import com.example.proyecto_movil.databinding.ActivityResgistroBinding
import com.example.proyecto_movil.model.Publicaciones
import com.squareup.picasso.Picasso
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

class PublicacionCompletaActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPublicacioCompletaBinding
    private lateinit var item:Publicaciones
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityPublicacioCompletaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        intent.extras?.let {
             item = Json.decodeFromString(it.get("item").toString()) as Publicaciones

        }
        Picasso.get().load(item.imagen).into(binding.imageView3)
        binding.imageView3
        binding.textView.text=item.titulo
        binding.textView4.text=item.tipo
        binding.textView3.text=item.tiempo
    }
}