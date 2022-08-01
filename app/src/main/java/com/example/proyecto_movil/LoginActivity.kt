package com.example.proyecto_movil

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.proyecto_movil.databinding.ActivityLoginBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class LoginActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        auth = Firebase.auth

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

        binding.recuperarContraseA.setOnClickListener{
            startActivity(Intent(this, MainActivity::class.java))

        }





    }
/*
    public override fun onStart() {
        super.onStart()
        val currentUser = auth.currentUser
        if (currentUser != null) {
            reload()
        }
    }*/


    private fun singIn(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    Log.d("TAG", "signInWithEmail:success")
                    reload(email)

                } else {
                    Log.w("TAG", "signInWithEmail:failure", task.exception)
                    Toast.makeText(
                        baseContext, "Correo o contraseña incorrectos",
                        Toast.LENGTH_SHORT
                    ).show()

                }

            }

    }

    private fun reload(email:String) {
        if(auth.currentUser!!.isEmailVerified){
            val homeIntent = Intent(this, MainActivity::class.java)
            homeIntent.putExtra("email",email)
            startActivity(homeIntent)
        }else{
            Toast.makeText(applicationContext, "Verifique su correo",Toast.LENGTH_LONG).show()

        }

    }
}