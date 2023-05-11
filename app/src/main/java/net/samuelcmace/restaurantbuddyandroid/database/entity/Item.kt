package net.samuelcmace.restaurantbuddyandroid.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

/**
 * Room entity representing the ITEM table in the database.
 *
 * @param id Field representing the primary key in the table. If it is set to null upon instantiation, Room will auto-generate a primary key.
 * @param dbId Field representing the primary key of the item in the APIs database.
 * @param name Field representing the name of the menu item.
 * @param cost Field representing the price which the customer will have to pay to purchase the item.
 * @param description Field representing the description of the item.
 * @param image Field representing a BASE-64 representation of an image passed over the HTTP API.
 */
@Entity(tableName = "ITEM")
data class Item(
    @PrimaryKey(autoGenerate = true) val id: Int?,
    @ColumnInfo(name = "ITEM_DB_ID") val dbId: Int?,
    @ColumnInfo(name = "ITEM_NAME") val name: String?,
    @ColumnInfo(name = "ITEM_COST") val cost: Double?,
    @ColumnInfo(name = "ITEM_DESCRIPTION") val description: String?,
    @ColumnInfo(name = "ITEM_IMAGE") val image: String?
) {

    /**
     * Method to instantiate a new instance of the ItemModel table without persisting it to the database.
     *
     * @param dbId Field representing the primary key of the item in the APIs database.
     * @param name Field representing the name of the menu item.
     * @param cost Field representing the price which the customer will have to pay to purchase the item.
     * @param description Field representing the description of the item.
     * @param image Field representing a BASE-64 representation of an image passed over the HTTP API.
     */
    @Ignore
    constructor(dbId: Int, name: String, cost: Double, description: String?, image: String?) : this(
        null,
        dbId,
        name,
        cost,
        description,
        image
    )

    /**
     * Method generating the string value of the itemModels in the table.
     * Used mainly for debugging purposes.
     *
     * @returns A string containing the fields stored in the table (used only for debugging).
     */
    override fun toString() =
        "id = $id, dbId = $dbId, name = $name, cost = $cost, description = $description, image = $image"

}
