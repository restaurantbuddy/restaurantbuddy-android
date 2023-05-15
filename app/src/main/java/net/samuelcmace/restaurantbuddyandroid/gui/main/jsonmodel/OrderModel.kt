package net.samuelcmace.restaurantbuddyandroid.gui.main.jsonmodel

import kotlinx.serialization.Serializable


/**
 * A JSON-serializable object used to place an order.
 *
 * @property menuItems A JSON array containing the primary keys of the objects (as they are stored in the database of the API).
 * @constructor Initializes a new instance of OrderModel
 */
@Serializable
data class OrderModel(
    val menuItems: List<Long>,
)
