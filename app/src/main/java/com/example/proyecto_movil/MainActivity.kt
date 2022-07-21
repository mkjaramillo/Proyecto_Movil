package com.example.proyecto_movil

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.proyecto_movil.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding:ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.idNavegacion.setOnItemSelectedListener {
            when(it.itemId){

                R.id.idMapa->{
                    startActivity(Intent(this,MapaActivity::class.java))
                    true}

                else->false


            }
        }
    }
}