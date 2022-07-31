package com.example.proyecto_movil.controladores

import android.content.Intent
import androidx.core.content.ContextCompat.startActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.proyecto_movil.MapaActivity
import com.example.proyecto_movil.R
import com.example.proyecto_movil.ui.fragments.BuscarFragment
import com.example.proyecto_movil.ui.fragments.InformacionFragment
import com.example.proyecto_movil.ui.fragments.PrincipalFragment
import com.example.proyecto_movil.ui.fragments.UsuarioFragment


class PrincipalController:ViewModel() {
    //intanciar tantos fragmentes como botones hay en la navegacion
    private  var principal= PrincipalFragment()
    private var buscar=BuscarFragment()
    private var usuario= UsuarioFragment()
    private var info=InformacionFragment()
    val fragmentActual : MutableLiveData<Fragment> by lazy {
        MutableLiveData<Fragment>()
    }
    val botonNavegationSeleccionado : MutableLiveData<Int> by lazy {
        MutableLiveData<Int>()
    }

    init {
        fragmentActual.postValue(principal)
        botonNavegationSeleccionado.postValue(3)
    }

    fun changeFragment(index:Int) : Boolean {
        changeTab(index)
        when(index) {

            R.id.idHome -> {
                fragmentActual.postValue(principal)
                return true
            }
            R.id.idUsuario -> {
                fragmentActual.postValue(usuario)
                return true
            }
            R.id.idBuscar -> {
                fragmentActual.postValue(buscar)
                return true
            }
            R.id.idInfo -> {
                fragmentActual.postValue(info)
                return true
            }
            else -> return false
        }
    }

    fun changeTab(index:Int) {
        botonNavegationSeleccionado.postValue(index)
    }



}