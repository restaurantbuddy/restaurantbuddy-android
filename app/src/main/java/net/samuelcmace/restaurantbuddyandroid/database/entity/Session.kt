package net.samuelcmace.restaurantbuddyandroid.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "SESSION")
data class Session(
    @PrimaryKey(autoGenerate = true) val id: Int?,
    @ColumnInfo(name = "SESSION_TOKEN") val token: String?
) {
    override fun toString() = "id = $id, token = $token"
}
