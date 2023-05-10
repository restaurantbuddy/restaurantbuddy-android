package net.samuelcmace.restaurantbuddyandroid.gui.main.model

import kotlinx.serialization.Serializable

@Serializable
data class ItemCollection(
    val items: List<Item>
)
