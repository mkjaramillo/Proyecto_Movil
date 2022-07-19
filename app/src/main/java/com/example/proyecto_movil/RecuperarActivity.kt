package com.example.proyecto_movil

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.proyecto_movil.databinding.ActivityLoginBinding
import com.example.proyecto_movil.databinding.ActivityRecuperarBinding

class RecuperarActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRecuperarBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRecuperarBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}