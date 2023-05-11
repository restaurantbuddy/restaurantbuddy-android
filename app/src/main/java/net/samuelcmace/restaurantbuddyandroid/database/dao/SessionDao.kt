package net.samuelcmace.restaurantbuddyandroid.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import net.samuelcmace.restaurantbuddyandroid.database.entity.Session

/**
 * DAO interface for the SESSION table in the database.
 */
@Dao
interface SessionDao {

    /**
     * Method to retrieve all the SESSION(s) stored in the database.
     * @return All the session tokens stored in the database. Ideally, there should be one and only one session token stored in the database.
     */
    @Query("SELECT * FROM SESSION")
    suspend fun getAll(): List<Session>

    /**
     * Method to add a new instance to the database.
     * @param sessions The new Session objects to be inserted into the database.
     */
    @Insert
    suspend fun insert(vararg sessions: Session)

    /**
     * Method to delete all active session tokens from the database (clear the authentication token cache).
     */
    @Query("DELETE FROM SESSION")
    suspend fun deleteAll()

}
