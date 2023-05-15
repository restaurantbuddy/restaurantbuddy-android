package net.samuelcmace.restaurantbuddyandroid.gui.main.jsonmodel

import kotlinx.serialization.Serializable

/**
 * JSON Entity representing a restaurant location.
 *
 * @property address Field representing the address of the location (assuming a US-based address).
 * @property city Field representing the city of the location (assuming a US-based address).
 * @property state Field representing the state of the location (assuming a US-based address).
 * @property zip Field representing the zip code of the location (assuming a US-based address).
 * @constructor Initializes a new instance of LocationModel.
 */
@Serializable
data class LocationModel(
    var id: Long?,
    var name: String?,
    var address: String?,
    val city: String?,
    val state: String?,
    val zip: String?
) {

    /**
     * Method to retrieve the string value of the address.
     */
    override fun toString(): String {
        return "$address, $city, $state $zip"
    }

}
