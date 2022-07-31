package com.example.proyecto_movil

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.example.proyecto_movil.controladores.PrincipalController
import com.example.proyecto_movil.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding:ActivityMainBinding
    //ViewModel
    private val activityViewModel : PrincipalController by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


activityViewModel.fragmentActual.observe(this, Observer {
    cambiarFragmento(it)
})
        activityViewModel.botonNavegationSeleccionado.observe(this, Observer {
            binding.idNavegacion.selectedItemId = it
        })

        binding.idNavegacion.setOnItemSelectedListener { it ->
            activityViewModel.changeFragment(it.itemId)
        }



/**
        binding.idNavegacion.setOnItemSelectedListener {
            when(it.itemId){

                R.id.idMapa->{
                    startActivity(Intent(this,MapaActivity::class.java))
                    true}

                else->false


            }
        }
**/
    }
    fun cambiarFragmento(fragmento : Fragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction
            .replace(binding.contenedorFragmentos.id, fragmento)
            .commit()
    }
}