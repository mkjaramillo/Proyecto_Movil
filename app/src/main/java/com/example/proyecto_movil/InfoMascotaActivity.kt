package com.example.proyecto_movil

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import com.example.proyecto_movil.databinding.ActivityInfoMascotaBinding

class InfoMascotaActivity : AppCompatActivity(){

    private lateinit var binding: ActivityInfoMascotaBinding
    private lateinit var listaDetalles : List<Detalle>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInfoMascotaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        showItem()

    }

    private fun showItem() {

        val opc1= Detalle("Tipo: ", "")
        val opc2= Detalle("Raza: ", "")
        val opc3= Detalle("Sexo: ", "")
        val opc4= Detalle("Pelo: ", "")
        val opc5= Detalle("Ojos: ", "")
        val opc6= Detalle("Chip: ", "")
        val opc7= Detalle("Collar: ", "")


        listaDetalles = listOf(opc1,opc2,opc3,opc4,opc5,opc6,opc7)

        val adapter = ArrayAdapter(this, R.layout.list_detalle,listaDetalles)

        //binding.autoCompleteDetalles.isClickable=true
        with(binding.autoCompleteDetalles){
            setAdapter(adapter)
           // onItemClickListener = this@InfoMascotaActivity
        }

    }

    //override fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
      //  val item = parent?.getItemAtPosition(position).toString()
       // Toast.makeText(this@InfoMascotaActivity,item,Toast.LENGTH_SHORT).show()
   // }
}