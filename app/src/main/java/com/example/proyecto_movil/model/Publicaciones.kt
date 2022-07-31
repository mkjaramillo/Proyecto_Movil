package com.example.proyecto_movil.model

import kotlinx.serialization.Serializable

@Serializable
data class Publicaciones(var imagen:String,var titulo:String,var raza:String?,var sexo:String?,var tiempo:String?,var lat:String?,var long:String?,var descripcion:String?, var telefono:String?,var tipo:String)
