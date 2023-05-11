package net.samuelcmace.restaurantbuddyandroid.gui.main.jsonmodel

import androidx.room.ColumnInfo
import kotlinx.serialization.Serializable

/**
 * JSON Entity representing a restaurant location.
 */
@Serializable
data class LocationModel(

    /**
     * Field representing the address of the location (assuming a US-based address).
     */
    @ColumnInfo(name = "LOCATION_ADDRESS") var address: String?,

    /**
     * Field representing the city of the location (assuming a US-based address).
     */
    @ColumnInfo(name = "LOCATION_CITY")
    val city: String?,

    /**
     * Field representing the state of the location (assuming a US-based address).
     */
    @ColumnInfo(name = "LOCATION_STATE")
    val state: String?,

    /**
     * Field representing the zip code of the location (assuming a US-based address).
     */
    @ColumnInfo(name = "LOCATION_ZIP")
    val zip: String?

) {

    /**
     * Method to retrieve the string value of the address.
     */
    override fun toString(): String {
        return "$address\n$city, $state $zip"
    }

}