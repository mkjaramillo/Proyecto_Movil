package com.example.proyecto_movil

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.proyecto_movil.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding:ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val bundle= intent.extras
        val userMail = bundle?.getString("email")
        Toast.makeText(this,userMail, Toast.LENGTH_SHORT).show()
       // binding.textView.text= userMail

        binding.idNavegacion.setOnItemSelectedListener {
            when(it.itemId){

                R.id.idMapa->{
                    val intent= Intent(this,MapaActivity::class.java).apply { putExtra("email",userMail)}
                    startActivity(intent)
                    true}

                else->false


            }
        }
    }
}