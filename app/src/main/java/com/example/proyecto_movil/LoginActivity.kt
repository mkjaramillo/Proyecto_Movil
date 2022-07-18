package com.example.proyecto_movil

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.proyecto_movil.databinding.ActivityAnuncioBinding

import com.example.proyecto_movil.databinding.ActivityLoginBinding
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUp()
    }

    private fun setUp() {
        title = "Autenticación"

        binding.buttonIngresar.setOnClickListener {
            //falta comprobar que los datos sean correctos!!!!!!!!!!!!!!!!!
            if (binding.editTextUser.text.isNotEmpty()
                && binding.editTextPassword.text.isNotEmpty()
            ) {
                FirebaseAuth.getInstance().signInWithEmailAndPassword(
                    binding.editTextUser.toString(), binding.editTextPassword.toString()
                ).addOnCompleteListener {
                    if (it.isSuccessful) {
                        showHome()
                    } else {
                        showAlert()
                    }
                }
            }
        }
    }

    private fun showAlert(){
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Error")
        builder.setMessage("Error de autenticación")
        builder.setPositiveButton("Aceptar",null)
        val dialog: AlertDialog= builder.create()
        dialog.show()
    }

    private fun showHome(){
      val homeIntent= Intent(this, HomeActivity::class.java).apply {
      }
        startActivity(homeIntent)
    }
}