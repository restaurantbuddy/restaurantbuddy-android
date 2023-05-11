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
    @ColumnInfo(name = "LOCATION_ADDRESS")
    private var address: String?,

    /**
     * Field representing the city of the location (assuming a US-based address).
     */
    @ColumnInfo(name = "LOCATION_CITY")
    private val city: String?,

    /**
     * Field representing the state of the location (assuming a US-based address).
     */
    @ColumnInfo(name = "LOCATION_STATE")
    private val state: String?,

    /**
     * Field representing the zip code of the location (assuming a US-based address).
     */
    @ColumnInfo(name = "LOCATION_ZIP")
    private val zip: String?

)
