package com.example.androidtraining.activity

import android.Manifest
import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Geocoder
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.androidtraining.R
import com.example.androidtraining.bottom_navigation.BottomNavigationActivity
import com.example.androidtraining.databinding.ActivityMapsBinding
import com.google.android.gms.common.api.Status
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.*
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.libraries.places.api.Places
import com.google.android.libraries.places.api.model.Place
import com.google.android.libraries.places.widget.AutocompleteSupportFragment
import com.google.android.libraries.places.widget.listener.PlaceSelectionListener
import java.util.*


class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private lateinit var binding: ActivityMapsBinding
    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient
    private lateinit var myAddress: String

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMapsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val actionBar = supportActionBar!!
        actionBar.title = "Maps"

        val apiKey = getString(R.string.google_maps_key)

        if (!Places.isInitialized()) {
            Places.initialize(applicationContext, apiKey)
        }

        val mapFragment = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        initAutoCompletePlaces()
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        mMap.uiSettings.isZoomControlsEnabled = true
        getCurrentLocation()
    }

    private fun initAutoCompletePlaces() {

            val autocompleteFragment =
                supportFragmentManager.findFragmentById(R.id.autocomplete_fragment)
                        as AutocompleteSupportFragment
            autocompleteFragment.setPlaceFields(
                listOf(
                    Place.Field.LAT_LNG,
                    Place.Field.ADDRESS,
                    Place.Field.NAME
                )
            )
            autocompleteFragment.setOnPlaceSelectedListener(object : PlaceSelectionListener {
                override fun onPlaceSelected(place: Place) {
                    placeMarker(place.latLng!!, place.name!!)
                }

                override fun onError(p0: Status) {
                    Toast.makeText(applicationContext,"Error: ${p0.statusMessage}",Toast.LENGTH_LONG).show()
                }

            })

    }

    private fun getCurrentLocation() {
        if (checkPermission()) {

            fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this)
            fusedLocationProviderClient.lastLocation.addOnSuccessListener(this) { location ->

                if (location != null) {
                    val addresses = Geocoder(this@MapsActivity, Locale.getDefault()).getFromLocation(
                        location.latitude,
                        location.longitude, 1
                    )
                    if (addresses.size > 0) {
                        myAddress = addresses[0].getAddressLine(0)
                    } else {
                        placeMarker(LatLng(-34.0, 151.0), "Sydney")
                    }

                    val currentLocation = LatLng(location.latitude, location.longitude)
                    placeMarker(currentLocation, myAddress)

                } else Toast.makeText(applicationContext,"Could not get the Location",Toast.LENGTH_LONG).show()
            }
        } else {
            requestLocationPermission()

            placeMarker(LatLng(-34.0, 151.0), "Sydney")

        }
    }

    private fun placeMarker(latlng: LatLng, addresses: String) {
        val markerOptions = MarkerOptions().position(latlng)

        markerOptions.title(addresses)
        mMap.addMarker(markerOptions)
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latlng, 15f))
    }

    private fun checkPermission(): Boolean {
        val result = ContextCompat.checkSelfPermission(this,Manifest.permission.ACCESS_FINE_LOCATION)
        return result == PackageManager.PERMISSION_GRANTED
    }

    private fun requestLocationPermission() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(this,Manifest.permission.ACCESS_FINE_LOCATION)
        ) {
            ActivityCompat.requestPermissions(this,arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),1)
        } else
            ActivityCompat.requestPermissions(this,arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),1)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == 1) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                getCurrentLocation()

            } else {

                val requestAgain =  shouldShowRequestPermissionRationale(Manifest.permission.ACCESS_FINE_LOCATION)
                if (requestAgain)
                {
                    Toast.makeText(applicationContext,"Location Permission Denied",Toast.LENGTH_LONG).show()}

                else {
                    Toast.makeText(applicationContext,
                        "Location Permission Denied, Go to settings and enable Permission",
                        Toast.LENGTH_LONG).show()

                    val builder = AlertDialog.Builder(this)
                    builder.setTitle("Permission Required")
                    builder.setMessage(
                        "Permission is required to get the Location Details. " +
                                "\nClick Permit to go to settings and enable Permission"
                    )
                    builder.setCancelable(false)
                    builder.setPositiveButton("Permit")
                    { _, _ ->
                        val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
                        val uri: Uri = Uri.fromParts("package", packageName, null)
                        intent.data = uri
                        startActivity(intent)
                    }
                    builder.setNegativeButton("Cancel")
                    { dialog, _ ->

                        dialog.dismiss()
                    }
                    val alert = builder.create()
                    alert.show()

                }
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_widget -> {
                val iMA = Intent(this, WidgetActivity::class.java)
                startActivity(iMA)
                finish()
            }
            R.id.action_RecyclerView -> {
                val iMA = Intent(this, RecyclerActivity::class.java)
                startActivity(iMA)
                finish()
            }
            R.id.action_Dialogs -> {
                val iMA = Intent(this, DialogsActivity::class.java)
                startActivity(iMA)
                finish()
            }
            R.id.action_CustomListView -> {
                val iMA = Intent(this, MediasActivity::class.java)
                startActivity(iMA)
                finish()
            }
            R.id.action_Image -> {
                val iMA = Intent(this, GridActivity::class.java)
                startActivity(iMA)
                finish()
            }
            R.id.action_Posts -> {
                val iMA = Intent(this, PostsActivity::class.java)
                startActivity(iMA)
                finish()
            }
            R.id.action_Tab -> {
                val iMA = Intent(this, TabLayoutViewPager::class.java)
                startActivity(iMA)
                finish()
            }
            R.id.action_BottomNavigation -> {
                val iMA = Intent(this, BottomNavigationActivity::class.java)
                startActivity(iMA)
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }
}