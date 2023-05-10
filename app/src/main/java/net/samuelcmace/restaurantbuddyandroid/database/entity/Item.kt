package net.samuelcmace.restaurantbuddyandroid.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

/**
 * Room entity representing the ITEM table in the database.
 */
@Entity(tableName = "ITEM")
data class Item(

    /**
     * Field representing the primary key in the table. If it is set to null upon instantiation,
     * Room will auto-generate a primary key.
     */
    @PrimaryKey(autoGenerate = true) val id: Int?,

    /**
     * Field representing the name of the menu item.
     */
    @ColumnInfo(name = "ITEM_NAME") val name: String,

    /**
     * Field representing the price which the customer will have to pay to purchase the item.
     */
    @ColumnInfo(name = "ITEM_COST") val cost: Double,

    /**
     * Field representing the description of the item.
     */
    @ColumnInfo(name = "ITEM_DESCRIPTION") val description: String?

) {

    /**
     * Method to instantiate a new instance of the Item table without persisting it to the database.
     */
    @Ignore
    constructor(name: String, cost: Double, description: String?) : this(null, name, cost, description)

    /**
     * Method generating the string value of the items in the table.
     * Used mainly for debugging purposes.
     */
    override fun toString() = "id = $id, name = $name, cost = $cost, description = $description"

}
