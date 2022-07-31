package com.example.proyecto_movil.controladores

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.proyecto_movil.model.Publicaciones
import kotlinx.coroutines.launch

class HomeController : ViewModel(){

    var listaData = MutableLiveData<List<Publicaciones>>()
    init {
        viewModelScope.launch {
            getItems()
        }
    }
    suspend fun getItems() {
      //aqui debo consultar de la base de datos
      /**  val ret = AvistamientoBL().getListaAvistamientos()
        retLiveData.postValue(ret)
        isLoading.postValue(false)**/
        val p = Publicaciones("https://www.florespedia.com/Imagenes/flores-bonitas.jpg","no encontrado","","","12","","","","","perdido")
        val ret: List<Publicaciones> = listOf(p,p,p,p,p,p)
        listaData.postValue(ret)

    }
}