package com.example.proyecto_movil

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.proyecto_movil.databinding.ActivityResgistroBinding
import com.example.proyecto_movil.utils.DataBaseInstance
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import java.util.*
import kotlin.collections.HashMap

class RegistroActivity : AppCompatActivity() {
    private lateinit var binding: ActivityResgistroBinding
    private lateinit var  auth: FirebaseAuth
    private lateinit var  database: FirebaseFirestore
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityResgistroBinding.inflate(layoutInflater)
       setContentView(binding.root)

        title = "Registro"
        var nombre=""
        var correo=""
        var password=""
        auth= DataBaseInstance.getDatabaseAuth()
        database=DataBaseInstance.getDataBaseFireStore()
       binding.idCrearCuenta.setOnClickListener(){
            nombre=binding.idNombreL.text.toString()
            correo=binding.idCorreoL.text.toString()
            password=binding.idPaswordL.text.toString()
            if (nombre.isNotEmpty()&&correo.isNotEmpty()&&password.isNotEmpty()){
                if(password.length>=6){
                    registrarUsuario(nombre,correo, password)
                }else{
                    Toast.makeText(this,"la contraseña debe tener mas de 6 caracteres",Toast.LENGTH_SHORT).show()
                }

            }else{
                Toast.makeText(this,"ingrese todos los campos",Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun registrarUsuario(n:String,c:String,p:String){

        auth.createUserWithEmailAndPassword(c,p).addOnCompleteListener { task ->
            if(task.isSuccessful){

                auth.currentUser!!.sendEmailVerification().addOnCompleteListener { ver ->
                    if (ver.isSuccessful) {
                        Toast.makeText(applicationContext, "Correo de verificación enviado a: " + c,Toast.LENGTH_LONG).show()


                        var id= auth.currentUser!!.uid
                        var hashMap : HashMap<String, String>
                                = HashMap<String,String> ()
                        hashMap.put("name" , n)
                        hashMap.put("correo" , c)
                        hashMap.put("password" ,p )

                        database.collection("Users").document(c).set(hashMap).addOnCompleteListener { task2 ->
                            if(task2.isSuccessful){
                                startActivity(Intent(this,LoginActivity::class.java))
                                Toast.makeText(applicationContext,"verique su correo",Toast.LENGTH_LONG).show()

                                finish()

                            }else{
                                Toast.makeText(applicationContext,"No se pudo crear los datos correctamente",Toast.LENGTH_LONG).show()
                            }
                        }.addOnFailureListener { exception ->
                            Toast.makeText(applicationContext,exception.localizedMessage,Toast.LENGTH_LONG).show()
                        }
                    }

                }
            }
        }.addOnFailureListener { exception ->
            Toast.makeText(applicationContext,exception.localizedMessage,Toast.LENGTH_LONG).show()
        }
    }
    }

