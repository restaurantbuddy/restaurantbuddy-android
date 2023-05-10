package net.samuelcmace.restaurantbuddyandroid

import net.samuelcmace.restaurantbuddyandroid.database.entity.Session

object AppConfig {

    private const val SERVER_PROTOCOL = "http"
    private const val SERVER_DOMAIN = "10.0.2.2"
    private const val SERVER_PORT = "8888"

    fun getServerUrl() = "$SERVER_PROTOCOL://$SERVER_DOMAIN:$SERVER_PORT/api/v1"

    var authToken: Session? = null

}
