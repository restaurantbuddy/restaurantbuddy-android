package net.samuelcmace.restaurantbuddyandroid.gui.main

import android.annotation.SuppressLint
import android.location.Geocoder
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.material.snackbar.Snackbar
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import net.samuelcmace.restaurantbuddyandroid.R
import net.samuelcmace.restaurantbuddyandroid.databinding.ActivityLocationsBinding
import net.samuelcmace.restaurantbuddyandroid.gui.main.jsonmodel.LocationModelCollection
import net.samuelcmace.restaurantbuddyandroid.service.LocationService
import pub.devrel.easypermissions.EasyPermissions
import java.util.*

/**
 * Activity used to load the restaurant locations and display them on a Google Map.
 */
class LocationsActivity : AppCompatActivity(), OnMapReadyCallback {

    /**
     * The map object associated with the activity.
     */
    private lateinit var mMap: GoogleMap

    /**
     * The binding object associated with the map activity.
     */
    private lateinit var binding: ActivityLocationsBinding

    /**
     * The binding object associated with the map activity.
     */
    private lateinit var mLocationService: LocationService

    /**
     * Method called by the Android API after the activity has been drawn.
     */
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        this.mLocationService = LocationService(this)

        binding = ActivityLocationsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

    }

    /**
     * Method called by the Android API once the map is available and ready to be used.
     */
    override fun onMapReady(googleMap: GoogleMap) {

        mMap = googleMap

        loadLocations()
        enableLocationDetection()

    }

    /**
     * Method called after the token has been verified to load the locations from the API.
     */
    @Suppress("DEPRECATION")
    private fun loadLocations() {
        this.mLocationService.getLocations({

            val jsonItems = Json.decodeFromString<LocationModelCollection>(it.toString())
            val geocoder = Geocoder(this)

            for (item in jsonItems.locations) {

                val address = geocoder.getFromLocationName(item.toString(), 1)

                if (address != null && address.size > 0) {
                    val coordinate = LatLng(address.first().latitude, address.first().longitude)
                    mMap.addMarker(MarkerOptions().position(coordinate).title("Marker in Sydney"))
                    mMap.moveCamera(CameraUpdateFactory.newLatLng(coordinate))
                }

            }

        }, {
            Toast.makeText(this, it, Toast.LENGTH_LONG).show()
        })
    }

    /**
     * Method called by the Google Maps API after location detection has been enabled.
     */
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this)
    }

    /**
     * Method called to load location detection on the Google Map.
     */
    @SuppressLint("MissingPermission")
    private fun enableLocationDetection() {
        val requiredContext = applicationContext

        if (EasyPermissions.hasPermissions(requiredContext, android.Manifest.permission.ACCESS_FINE_LOCATION)) {
            this.mMap.isMyLocationEnabled = true
        } else {
            val action = Snackbar.make(
                findViewById(android.R.id.content),
                "Location permissions are required to display your current location.",
                Snackbar.LENGTH_INDEFINITE
            ).setAction(R.string.ok) {
                EasyPermissions.requestPermissions(
                    this,
                    "Location permissions are needed to show your position on the map.",
                    RC_LOCATION,
                    android.Manifest.permission.ACCESS_FINE_LOCATION
                )
            }.show()
        }
    }

    companion object {

        /**
         * The request code associated with the location access request.
         * This access code is referenced by the EasyPermissions library when requesting location permissions on the device.
         */
        const val RC_LOCATION = 10

    }

}