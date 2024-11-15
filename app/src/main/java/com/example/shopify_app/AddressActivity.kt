package com.example.shopify_app

import android.annotation.SuppressLint
import android.content.Intent
import android.location.Address
import android.location.Geocoder
import android.media.Image
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class AddressActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var inputEditText: EditText
    private lateinit var confirmButton: Button
    private lateinit var backArrow : ImageView

    private lateinit var googleMap: GoogleMap
    private lateinit var fusedLocationProvider: FusedLocationProviderClient

    var latitude: Double = 0.0
    var longitude: Double = 0.0

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_address)

        inputEditText = findViewById(R.id.address_input)
        confirmButton = findViewById(R.id.confirm_address_button)

        fusedLocationProvider = LocationServices.getFusedLocationProviderClient(this)

        val mapFragment = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        backArrow = findViewById(R.id.backArrowImage)
        backArrow.setOnClickListener {
            startActivity(Intent(this@AddressActivity,ProfileActivity::class.java))
            overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
        }
    }

    override fun onMapReady(p0: GoogleMap) {

        googleMap = p0

        confirmButton.setOnClickListener {
            getLocationFromString(inputEditText.text.toString())
            val latLng = LatLng(latitude, longitude)

            googleMap.addMarker(
                MarkerOptions()
                    .position(latLng)
                    .title(inputEditText.text.toString())
            )

            val cameraUpdate = CameraUpdateFactory.newLatLngZoom(latLng, 12f)
            googleMap.moveCamera(cameraUpdate)

            Handler(Looper.getMainLooper()).postDelayed({
                    Toast.makeText(applicationContext,"Address saved!",Toast.LENGTH_LONG).show()
                    val intent = Intent(this,ProfileActivity::class.java)
                    startActivity(intent)
                    finish()
                },4000)
        }
    }

    fun getLocationFromString(input: String) {
        val geocoder = Geocoder(this)
        val list: MutableList<Address> ?= geocoder.getFromLocationName(input, 5)

        if(list.isNullOrEmpty())
        {
            return
        }

        latitude = list[0].latitude
        longitude = list[0].longitude
    }
}