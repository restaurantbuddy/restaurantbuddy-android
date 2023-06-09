package net.samuelcmace.restaurantbuddyandroid.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import net.samuelcmace.restaurantbuddyandroid.database.entity.Item

/**
 * Dao interface representing the ITEM table in the database.
 */
@Dao
interface ItemDao {

    /**
     * Method to retrieve all instances of the ITEM table stored in the database.
     *
     * @return A list of the items retrieved from the database.
     */
    @Query("SELECT * FROM ITEM")
    suspend fun getAll(): MutableList<Item>

    /**
     * Method to insert (or persist) a new ITEM into the database.
     *
     * @param items The new Item objects to be inserted into the database.
     */
    @Insert
    suspend fun insert(vararg items: Item)

    /**
     * Method to delete the corresponding item from the database.
     *
     * @param items The Item objects to be deleted from the database.
     */
    @Delete
    suspend fun delete(vararg items: Item)

    /**
     * Method to clear all itemModels from the database (clear the cart).
     */
    @Query("DELETE FROM ITEM")
    suspend fun clear()

}
