package net.samuelcmace.restaurantbuddyandroid.gui.main

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.material.snackbar.Snackbar
import net.samuelcmace.restaurantbuddyandroid.R
import net.samuelcmace.restaurantbuddyandroid.databinding.ActivityLocationsBinding
import pub.devrel.easypermissions.EasyPermissions

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
     * Method called by the Android API after the activity has been drawn.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

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

        // Add a marker in Sydney and move the camera
        val sydney = LatLng(-34.0, 151.0)
        mMap.addMarker(MarkerOptions().position(sydney).title("Marker in Sydney"))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney))
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