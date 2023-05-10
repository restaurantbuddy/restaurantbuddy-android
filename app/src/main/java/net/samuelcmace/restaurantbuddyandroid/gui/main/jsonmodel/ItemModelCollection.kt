package net.samuelcmace.restaurantbuddyandroid.gui.main.jsonmodel

import kotlinx.serialization.Serializable

@Serializable
data class ItemModelCollection(
    val items: List<ItemModel>
)
