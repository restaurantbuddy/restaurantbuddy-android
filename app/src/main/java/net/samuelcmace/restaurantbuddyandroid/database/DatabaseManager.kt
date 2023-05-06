package net.samuelcmace.restaurantbuddyandroid.database

import androidx.room.Database
import androidx.room.RoomDatabase
import net.samuelcmace.restaurantbuddyandroid.database.dao.SessionDao
import net.samuelcmace.restaurantbuddyandroid.database.entity.Session

@Database(entities = [Session::class], version = 1)
abstract class DatabaseManager : RoomDatabase() {

    companion object {
        const val DATABASE_NAME = "restaurantbuddy"
    }

    abstract fun sessionDao(): SessionDao

}
