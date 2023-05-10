package net.samuelcmace.restaurantbuddyandroid.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import net.samuelcmace.restaurantbuddyandroid.database.entity.Session

@Dao
interface SessionDao {

    @Query("SELECT * FROM SESSION")
    suspend fun getAll(): List<Session>

    @Insert
    suspend fun insertAll(vararg sessions: Session)

    @Delete
    suspend fun deleteAll(vararg sessions: Session)

}
