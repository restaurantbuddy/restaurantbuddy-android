package net.samuelcmace.restaurantbuddyandroid.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * DAO Interface representing access to the ITEM table in the SQLite database.
 */
@Entity(tableName = "SESSION")
data class Session(

    /**
     * Field representing the primary key in the table. If it is set to null upon instantiation,
     * Room will auto-generate a primary key.
     */
    @PrimaryKey(autoGenerate = true) val id: Int?,

    /**
     * Field representing the session token (used for authentication purposes). When sent to the server via a REST-ful
     * HTTP request, the text 'Bearer ' must be sent along with the key in the 'Authorization' header of the request.
     */
    @ColumnInfo(name = "SESSION_TOKEN") val token: String?

) {

    /**
     * Method generating the string value of the items in the table.
     * Used mainly for debugging purposes.
     */
    override fun toString() = "id = $id, token = $token"

}
