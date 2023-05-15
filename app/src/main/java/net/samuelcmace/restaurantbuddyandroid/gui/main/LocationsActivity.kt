package net.samuelcmace.restaurantbuddyandroid.gui.main

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.material.snackbar.Snackbar
import com.google.maps.GeoApiContext
import com.google.maps.GeocodingApi
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import net.samuelcmace.restaurantbuddyandroid.BuildConfig
import net.samuelcmace.restaurantbuddyandroid.R
import net.samuelcmace.restaurantbuddyandroid.databinding.ActivityLocationsBinding
import net.samuelcmace.restaurantbuddyandroid.jsonmodel.LocationModelCollection
import net.samuelcmace.restaurantbuddyandroid.service.LocationService
import pub.devrel.easypermissions.AfterPermissionGranted
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
     *
     * @param savedInstanceState The object to reference the previously-created activity if it is being recreated.
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
     *
     * @param googleMap The Google Map object that is now readily-initialized by the API.
     */
    override fun onMapReady(googleMap: GoogleMap) {

        mMap = googleMap

        enableLocationDetection()

        Toast.makeText(this, "Loading locations... one moment...", Toast.LENGTH_LONG).show()
        loadLocations()

    }

    /**
     * Method called after the token has been verified to load the locations from the API.
     */
    private fun loadLocations() {

        this.mLocationService.getLocations({

            try {
                val jsonItems = Json.decodeFromString<LocationModelCollection>(it.toString())
                val geocoder = GeoApiContext.Builder().apiKey(BuildConfig.MAPS_API_KEY).build()

                val locations = ArrayList<LatLng>()

                for (item in jsonItems.locations) {

                    val results = GeocodingApi.geocode(geocoder, item.toString()).await()

                    if (results.isNotEmpty()) {
                        val coordinate =
                            LatLng(results.first().geometry.location.lat, results.first().geometry.location.lng)
                        locations.add(coordinate)
                        mMap.addMarker(MarkerOptions().position(coordinate).title(item.name).snippet(item.toString()))
                        mMap.moveCamera(CameraUpdateFactory.newLatLng(coordinate))
                    }

                }

                val lastLocation = locations.last()
                this.mMap.animateCamera(
                    CameraUpdateFactory.newLatLngZoom(
                        lastLocation,
                        12.0f
                    )
                )

            } catch (e: Exception) {
                e.localizedMessage?.let { errorMessage ->
                    Toast.makeText(this, errorMessage, Toast.LENGTH_LONG).show()
                }
            }

        }, {
            Toast.makeText(this, it, Toast.LENGTH_LONG).show()
        })

    }

    /**
     * Method called by the Google Maps API after location detection has been enabled.
     *
     * @param requestCode The request code sent to the Google API.
     * @param permissions The permissions requested by the activity.
     * @param grantResults The results sent back after the permissions have been granted.
     */
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this)
    }

    /**
     * Method called to load location detection on the Google Map.
     */
    @SuppressLint("MissingPermission")
    @AfterPermissionGranted(RC_LOCATION)
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

    /**
     * Method called by the Android API to load the menu bar for the activity.
     *
     * @param menu The menu to be inflated.
     * @return A boolean containing the result of the menu inflation process.
     */
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_locations, menu)
        return super.onCreateOptionsMenu(menu)
    }

    /**
     * Method called by the Android API after a menu item has been selected.
     *
     * @param item The menu item that was selected.
     * @return A boolean containing the result of the menu item selection process.
     */
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {

            R.id.itBack -> {
                finish()
            }

        }
        return super.onOptionsItemSelected(item)
    }

}