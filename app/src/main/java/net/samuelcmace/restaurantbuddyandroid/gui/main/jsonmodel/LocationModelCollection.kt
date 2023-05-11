package net.samuelcmace.restaurantbuddyandroid.gui.main.jsonmodel

import kotlinx.serialization.Serializable

/**
 * JSON serialization entity representing a collection of locations sent from the API in JSON-format.
 */
@Serializable
data class LocationModelCollection(

    /**
     * The list of locations contained in the JSON response.
     */
    val locations: List<LocationModel>

)
