package net.samuelcmace.restaurantbuddyandroid.gui.main.model

import kotlinx.serialization.Serializable

@Serializable
data class Item(
    val id: Int,
    val name: String,
    val price: Double,
    val description: String,
    val image: String?,
    val menus: List<String>?
)
