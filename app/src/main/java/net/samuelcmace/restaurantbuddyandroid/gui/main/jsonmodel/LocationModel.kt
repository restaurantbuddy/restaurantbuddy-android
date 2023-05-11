package net.samuelcmace.restaurantbuddyandroid.gui.main.jsonmodel

import androidx.room.ColumnInfo
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
    @ColumnInfo(name = "LOCATION_ADDRESS") var address: String?,
    @ColumnInfo(name = "LOCATION_CITY") val city: String?,
    @ColumnInfo(name = "LOCATION_STATE") val state: String?,
    @ColumnInfo(name = "LOCATION_ZIP") val zip: String?
) {

    /**
     * Method to retrieve the string value of the address.
     */
    override fun toString(): String {
        return "$address\n$city, $state $zip"
    }

}
