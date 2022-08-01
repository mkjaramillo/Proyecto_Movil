package com.example.proyecto_movil.ui.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.proyecto_movil.adapters.AdapterPublicacion
import com.example.proyecto_movil.controladores.HomeController
import com.example.proyecto_movil.databinding.FragmentPrincipalBinding
import com.example.proyecto_movil.model.Publicaciones
import com.example.proyecto_movil.ui.activities.PublicacionCompletaActivity
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json


class PrincipalFragment : Fragment() {
    private var _binding: FragmentPrincipalBinding? = null
    private val binding get() = _binding!!
    private val homeController : HomeController by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {
        // Inflate the layout for this fragment
        _binding =FragmentPrincipalBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        homeController.listaData.observe(viewLifecycleOwner, Observer { items ->
            loadRecyclerView(items)
        })

    }
    private fun loadRecyclerView(items : List<Publicaciones>) {
        binding.listaMascotas.layoutManager =
            GridLayoutManager(binding.listaMascotas.context,2)
        binding.listaMascotas.adapter = AdapterPublicacion(items) { manageClickItem(it) }
    }
    private fun manageClickItem(item: Publicaciones) {
        val intent = Intent(activity?.baseContext, PublicacionCompletaActivity::class.java)
        val itemJson = Json.encodeToString(item)
        intent.putExtra("item", itemJson)
        startActivity(intent)
    }
}