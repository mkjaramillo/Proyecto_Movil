package com.example.proyecto_movil

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Address
import android.location.Geocoder
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.proyecto_movil.databinding.ActivityMapaBinding
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import java.util.*


class MapaActivity : AppCompatActivity(), OnMapReadyCallback {
    private lateinit var binding: ActivityMapaBinding
    private lateinit var map: GoogleMap
    private lateinit var posicion: LatLng

    companion object {
        const val REQUEST_CODE_LOCATION = 0
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        var anunciarFragment = AnunciarFragment()

        binding = ActivityMapaBinding.inflate(layoutInflater)


        val bundle = intent.extras
        val datoMail = bundle?.getString("email")
        //Toast.makeText(this,datoMail,Toast.LENGTH_SHORT).show()

        binding.textE.text = datoMail


        binding.botonPata.setOnClickListener() {

            posicion = map.cameraPosition.target
            getCompleteAddressString(posicion.latitude, posicion.longitude)

            if (datoMail != null) {
                setCurrentFragment(anunciarFragment, datoMail)
            }
            true
        }

        setContentView(binding.root)
        binding.idNavegacion.setOnItemSelectedListener {
            when (it.itemId) {

                R.id.idHome -> {
                    startActivity(Intent(this, MainActivity::class.java))
                    true
                }
                else -> false
            }
        }
        createFragment()
    }

    private fun setCurrentFragment(fragment: Fragment, userMail: String) {

        var dato = Bundle()
        dato.putString("email", userMail)

        fragment.arguments = dato
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.containerAnuncioView, fragment)
            commit()
        }
    }

    private fun createFragment() {
        val mapFragment =
            supportFragmentManager.findFragmentById(binding.map.id) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(mapa: GoogleMap) {
        map = mapa;
        createMarket()

        map.setOnCameraMoveStartedListener {
            posicion = map.cameraPosition.target
        }
        enabledLocation()

    }

    private fun createMarket() {
        val coordinates = LatLng(-0.19070536581060846, -78.4693941163642)
        val market = MarkerOptions().position(coordinates).title("Ecuador-Quito")
        map.addMarker(market)
        map.animateCamera(
            CameraUpdateFactory.newLatLngZoom(coordinates, 18f), 4800, null
        )
    }

    private fun isLocationPermissionGranted() =
        ContextCompat.checkSelfPermission(
            this,
            Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED

    private fun enabledLocation() {
        if (!::map.isInitialized) return
        if (isLocationPermissionGranted()) {
            if (ActivityCompat.checkSelfPermission(
                    this,
                    Manifest.permission.ACCESS_FINE_LOCATION
                ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                    this,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return
            }
            map.isMyLocationEnabled = true
        } else {
            requestLocation()
        }
    }

    private fun requestLocation() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            )
        ) {
            Toast.makeText(this, "ve a ajustes y acepta los permisos", Toast.LENGTH_SHORT).show()
        } else {
            ActivityCompat.requestPermissions(
                this, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                REQUEST_CODE_LOCATION
            )
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        when (requestCode) {
            REQUEST_CODE_LOCATION -> if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                if (ActivityCompat.checkSelfPermission(
                        this,
                        Manifest.permission.ACCESS_FINE_LOCATION
                    ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                        this,
                        Manifest.permission.ACCESS_COARSE_LOCATION
                    ) != PackageManager.PERMISSION_GRANTED
                ) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    return
                }
                map.isMyLocationEnabled = true
            } else {
                Toast.makeText(this, "ve a ajustes y acepta los permisos", Toast.LENGTH_SHORT)
                    .show()

            }
            else -> {}
        }
    }

    override fun onResumeFragments() {
        super.onResumeFragments()
        if (!isLocationPermissionGranted()) {
            if (ActivityCompat.checkSelfPermission(
                    this,
                    Manifest.permission.ACCESS_FINE_LOCATION
                ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                    this,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return
            }
            map.isMyLocationEnabled = false
            Toast.makeText(this, "ve a ajustes y acepta los permisos", Toast.LENGTH_SHORT).show()

        }
    }


    private fun getCompleteAddressString(LATITUDE: Double, LONGITUDE: Double): String? {
        var strAdd = ""
        val geocoder = Geocoder(this, Locale.getDefault())
        try {
            val addresses: List<Address>? = geocoder.getFromLocation(LATITUDE, LONGITUDE, 1)
            if (addresses != null) {
                val returnedAddress: Address = addresses[0]
                val strReturnedAddress = StringBuilder("")
                for (i in 0..returnedAddress.getMaxAddressLineIndex()) {
                    strReturnedAddress.append(returnedAddress.getAddressLine(i)).append("\n")
                }
                strAdd = strReturnedAddress.toString()

                Toast.makeText(
                    this,
                    "Tu localización es: " + strReturnedAddress.toString(),
                    Toast.LENGTH_SHORT
                ).show()

                Intent(this, MapaActivity::class.java).apply {
                    putExtra("localización", strReturnedAddress.toString())
                }


            } else {

                Toast.makeText(
                    this,
                    "My Current loction address" + "No Address returned!",
                    Toast.LENGTH_SHORT
                ).show()

            }
        } catch (e: Exception) {
            e.printStackTrace()

            Toast.makeText(
                this,
                "My Current loction address" + "Canont get Address!",
                Toast.LENGTH_SHORT
            ).show()

        }
        return strAdd
    }

}