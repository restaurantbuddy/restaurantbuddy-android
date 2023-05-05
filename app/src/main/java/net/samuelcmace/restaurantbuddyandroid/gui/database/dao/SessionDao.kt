package net.samuelcmace.restaurantbuddyandroid.gui.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import net.samuelcmace.restaurantbuddyandroid.gui.database.data.Session

@Dao
interface SessionDao {

    @Query("SELECT * FROM SESSION")
    suspend fun getAll(): List<Session>

    @Insert
    suspend fun insertAll(vararg sessions: Session)

}
