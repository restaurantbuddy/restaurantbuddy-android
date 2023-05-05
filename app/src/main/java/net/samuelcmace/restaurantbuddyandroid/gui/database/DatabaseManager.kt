package net.samuelcmace.restaurantbuddyandroid.gui.database

import androidx.room.Database
import androidx.room.RoomDatabase
import net.samuelcmace.restaurantbuddyandroid.gui.database.dao.SessionDao
import net.samuelcmace.restaurantbuddyandroid.gui.database.data.Session

@Database(entities = [Session::class], version = 1)
abstract class DatabaseManager : RoomDatabase() {

    companion object {
        const val DATABASE_NAME = "restaurantbuddy"
    }

    abstract fun sessionDao(): SessionDao

}
