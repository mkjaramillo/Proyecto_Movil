package com.example.proyecto_movil

import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import com.example.proyecto_movil.databinding.ActivityMascotaBinding
import com.example.proyecto_movil.utils.DataBaseInstance
import com.google.firebase.firestore.FirebaseFirestore
import java.text.SimpleDateFormat
import java.util.*

class MascotaActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMascotaBinding
    private lateinit var database: FirebaseFirestore

    var minimoImagen = false
    var photo: String = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        database = DataBaseInstance.getDataBaseFireStore()

        binding = ActivityMascotaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val bundle = intent.extras
        val datoMail = bundle?.getString("mail")
        Toast.makeText(this, datoMail, Toast.LENGTH_SHORT).show()
        binding.emailTx.text = datoMail.toString()

        val sdf = SimpleDateFormat("dd/M/yyyy")
        val currentDate = sdf.format(Date())
        binding.textFecha.setText(currentDate)

        binding.imagenMascota.setOnClickListener {
            requestPermissions()
        }

        binding.btnPublicar.setOnClickListener{
            if(minimoImagen==true){
                if (datoMail != null) {
                    registroMascota(photo,datoMail)
                }
            }else{
                Toast.makeText(this, "Debes ingresar mínimo una imagen." , Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun registroMascota(photo: String, datoMail:String) {

        var sexo = ""
        binding.sexoMacho.setOnClickListener {
            sexo = "Macho"
        }
        binding.sexoHembra.setOnClickListener {
            sexo = "Hembra"
        }
        binding.sexoNoSe.setOnClickListener {
            sexo = "No sé"
        }

        binding.btnPublicar.setOnClickListener {
            var hashMap: HashMap<String, String> = HashMap<String, String>()
            hashMap.put("titulo", binding.tituloMascota.text.toString())
            hashMap.put("descripción", binding.descripcionMascota.text.toString())
            hashMap.put("tipo", binding.tipoMascota.text.toString())
            hashMap.put("raza", binding.razaMascota.text.toString())
            hashMap.put("sexo", sexo)
            hashMap.put("fecha", binding.textFecha.text.toString())
            hashMap.put("zona", binding.textZona.text.toString())
            hashMap.put("teléfono", binding.telefonoUsuario.text.toString())
            hashMap.put("foto", binding.imagenMascota.toString())

            database.collection("Users").document(datoMail.toString()).collection("mascotas")
                .document().set(hashMap).addOnCompleteListener { task2 ->
                    if (task2.isSuccessful) {
                        Toast.makeText(this, "Publicado con exito", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(this, "Error al publicar", Toast.LENGTH_SHORT).show()
                    }
                }.addOnFailureListener { exception ->
                    Toast.makeText(
                        applicationContext,
                        exception.localizedMessage,
                        Toast.LENGTH_LONG
                    ).show()
                }
        }

    }

    private fun requestPermissions() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            when {
                ActivityCompat.checkSelfPermission(
                    this,
                    android.Manifest.permission.READ_EXTERNAL_STORAGE
                ) == PackageManager.PERMISSION_GRANTED -> {
                    pickPhotoFromGallery()
                }
                else -> requestPermissionLauncher.launch(android.Manifest.permission.READ_EXTERNAL_STORAGE)
            }
        } else {
            pickPhotoFromGallery()
        }
    }

    private val requestPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        if (isGranted) {
            pickPhotoFromGallery()
        } else {
            Toast.makeText(this, "necesitas permitir los permisos de la cámara", Toast.LENGTH_SHORT)
                .show()
        }
    }

    private val startForActivityGallery = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val data = result.data?.data
            binding.imagenMascota.setImageURI(data)
            photo = data.toString()
            minimoImagen=true
            Toast.makeText(this, "Si se selecciono una foto", Toast.LENGTH_SHORT)
                .show()

        }else{
            minimoImagen=false
            Toast.makeText(this, "No se selecciono una foto", Toast.LENGTH_SHORT)
                .show()
        }
    }


    private fun pickPhotoFromGallery() {
        val intent = Intent(Intent.ACTION_GET_CONTENT)
        intent.type = "image/+*"
        startForActivityGallery.launch(intent)
    }


}