package net.samuelcmace.restaurantbuddyandroid.gui.main.jsonmodel

import kotlinx.serialization.Serializable
import net.samuelcmace.restaurantbuddyandroid.database.entity.Item

@Serializable
data class ItemModel(
    val id: Int,
    val name: String,
    val price: Double,
    val description: String,
    val image: String?,
    val menus: List<String>?
) {

    fun getDBItem(): Item {
        return Item(id, name, price, description, image)
    }

}
