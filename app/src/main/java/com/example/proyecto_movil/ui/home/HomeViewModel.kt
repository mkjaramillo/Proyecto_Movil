package com.example.proyecto_movil.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class HomeViewModel : ViewModel() {
private val _text = MutableLiveData<String>().apply {
    value="Esta es la vista home"

}
    val text: LiveData<String> = _text
}
