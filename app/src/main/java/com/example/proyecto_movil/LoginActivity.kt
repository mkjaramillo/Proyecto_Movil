package com.example.proyecto_movil

import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.proyecto_movil.databinding.ActivityLoginBinding
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.buttonRegistro.setOnClickListener {
            startActivity(Intent(this, RegistroActivity::class.java))
        }

        binding.buttonIngresar.setOnClickListener {
            val mEmail = binding.editTextUser.text.toString()
            val mPassword = binding.editTextPassword.text.toString()

            when {
                binding.editTextUser.text.isEmpty() || binding.editTextPassword.text.isEmpty() -> {
                    Toast.makeText(
                        baseContext, "Correo o contraseña incorrectos.",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                else -> {
                    singIn(mEmail, mPassword)
                }

            }
        }
    }

    private fun singIn(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this) { task ->
            if (task.isSuccessful) {
                Log.d(TAG, "signInWithEmail:success")
                reload()

            } else {
                Log.w(TAG, "signInWithEmail:failure", task.exception)
                Toast.makeText(
                    baseContext, "Authentication failed",
                    Toast.LENGTH_SHORT
                ).show()

            }

        }

    }

    private fun reload(){
        super.onStart()
        val currentUser = auth.currentUser
        if( currentUser != null){
            reload()
        }
    }

    private fun showHome() {
        val homeIntent = Intent(this, HomeActivity::class.java)
        startActivity(homeIntent)
    }
}