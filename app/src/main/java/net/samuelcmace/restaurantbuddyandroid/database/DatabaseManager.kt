package net.samuelcmace.restaurantbuddyandroid.database

import androidx.room.Database
import androidx.room.RoomDatabase
import net.samuelcmace.restaurantbuddyandroid.database.dao.ItemDao
import net.samuelcmace.restaurantbuddyandroid.database.dao.SessionDao
import net.samuelcmace.restaurantbuddyandroid.database.entity.Item
import net.samuelcmace.restaurantbuddyandroid.database.entity.Session

/**
 * Class representing the Room SQLite database manager for the project.
 */
@Database(entities = [Session::class, Item::class], version = 1, exportSchema = false)
abstract class DatabaseManager : RoomDatabase() {

    /**
     * Companion object containing the database name.
     */
    companion object {
        /**
         * The filename to be used when accessing the database.
         */
        const val DATABASE_NAME = "restaurantbuddy"
    }

    /**
     * Method to instantiate a new instance of the SessionDao.
     *
     * @return A new automatically-implemented instance of the SessionDao interface.
     */
    abstract fun sessionDao(): SessionDao

    /**
     * Method to instantiate a new instance of ItemDao.
     *
     * @return A new automatically-implemented instance of the ItemDao interface.
     */
    abstract fun itemDao(): ItemDao

}
