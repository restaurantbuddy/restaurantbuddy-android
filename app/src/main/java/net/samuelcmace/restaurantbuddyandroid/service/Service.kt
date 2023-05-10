package net.samuelcmace.restaurantbuddyandroid.service

import android.content.Context
import androidx.room.Room
import net.samuelcmace.restaurantbuddyandroid.database.DatabaseManager
import net.samuelcmace.restaurantbuddyandroid.database.dao.SessionDao

abstract class Service(context: Context) {

    protected var mContext: Context
    protected var mDatabaseManager: DatabaseManager
    protected var mSessionDao: SessionDao

    init {
        this.mContext = context
        this.mDatabaseManager = Room.databaseBuilder(
            context,
            DatabaseManager::class.java, DatabaseManager.DATABASE_NAME
        ).build()
        this.mSessionDao = this.mDatabaseManager.sessionDao()
    }

}