package net.samuelcmace.restaurantbuddyandroid.gui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.room.Room
import kotlinx.coroutines.launch
import net.samuelcmace.restaurantbuddyandroid.R
import net.samuelcmace.restaurantbuddyandroid.database.DatabaseManager
import net.samuelcmace.restaurantbuddyandroid.database.dao.SessionDao

class MainActivity : AppCompatActivity() {

    private lateinit var mDatabaseManager: DatabaseManager
    private lateinit var mSessionDao: SessionDao

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        this.mDatabaseManager = Room.databaseBuilder(
            applicationContext,
            DatabaseManager::class.java, DatabaseManager.DATABASE_NAME
        ).build()
        this.mSessionDao = this.mDatabaseManager.sessionDao()

        this.lifecycleScope.launch {
            mSessionDao.getAll()
        }
    }

}