package net.samuelcmace.restaurantbuddyandroid.jsonmodel

import kotlinx.serialization.Serializable

/**
 * JSON-serializable object wrapper containing multiple ItemModels.
 *
 * @property items The ItemModels in question.
 * @constructor Initialized a new instance of ItemModelCollection.
 */
@Serializable
data class ItemModelCollection(
    val items: List<ItemModel>
)
