package com.example.foodapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.Navigation

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.example.foodapp.databinding.ActivityMapsBinding
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.material.snackbar.Snackbar

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private lateinit var binding: ActivityMapsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMapsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        binding.buttonKonumaGit.setOnClickListener {
            //37.760327893329865, 30.55661638729256,13z
            val konum = LatLng(37.760327893329865,30.55661638729256)
            mMap.addMarker(MarkerOptions().position(konum).title("Hollanda").icon(BitmapDescriptorFactory.fromResource(R.drawable.konum_resim)))
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(konum,14.75f))
            mMap.mapType = GoogleMap.MAP_TYPE_NORMAL
        }
        binding.buttonKonumuUygula.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            //Snackbar.make(it,"Konum başarıyla seçildi",Snackbar.LENGTH_SHORT).show() calismiyor.
        }
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        val turkey = LatLng(39.5302237, 31.9456957)
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(turkey,5f))
        mMap.addMarker(MarkerOptions().position(turkey).title("Marker in Turkey"))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(turkey))
    }
}
