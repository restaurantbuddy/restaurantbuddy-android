package net.samuelcmace.restaurantbuddyandroid.jsonmodel

import kotlinx.serialization.Serializable

/**
 * JSON serialization entity representing a collection of locations sent from the API in JSON-format.
 *
 * @property locations The list of locations contained in the JSON response.
 * @constructor Initializes a new instance of LocationModelCollection.
 */
@Serializable
data class LocationModelCollection(
    val locations: List<LocationModel>
)
