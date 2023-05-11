package net.samuelcmace.restaurantbuddyandroid.gui.main.jsonmodel

import kotlinx.serialization.Serializable
import net.samuelcmace.restaurantbuddyandroid.database.entity.Item

/**
 * A JSON-serializable object used in all API requests involving items.
 *
 * @property id The primary key of the object (as it is stored in the API's database).
 * @property name The name of the item.
 * @property price The price that the customer must pay to receive the item.
 * @property description The description of the item.
 * @property image A String containing a BASE-64 encoded image.
 * @property menus The menus on which the item is featured.
 * @constructor
 */
@Serializable
data class ItemModel(
    val id: Int,
    val name: String,
    val price: Double,
    val description: String,
    val image: String?,
    val menus: List<String>?
) {

    /**
     * Gets the Room-entity-equivalent of the JSON-serializable object.
     *
     * @return A Room object that can be persisted to a database if so desired.
     */
    fun getDBItem(): Item {
        return Item(id, name, price, description, image)
    }

}
