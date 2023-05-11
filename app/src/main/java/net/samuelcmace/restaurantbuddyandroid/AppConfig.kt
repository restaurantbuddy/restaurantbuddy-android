package net.samuelcmace.restaurantbuddyandroid

import net.samuelcmace.restaurantbuddyandroid.database.entity.Session

/**
 * Singleton object representing entities shared across all entities used in the application.
 */
object AppConfig {

    /**
     * The server protocol used for accessing the RestaurantBuddy API (for example, http or https).
     */
    private const val SERVER_PROTOCOL = "http"

    /**
     * The domain name or IP address that the app should be programmed to reference.
     */
    private const val SERVER_DOMAIN = "10.0.2.2"

    /**
     * The port on which the API listens for requests.
     */
    private const val SERVER_PORT = "8888"

    /**
     * Gets a string containing the entire server url, including the server protocol, domain, port, and root route.
     *
     * @return The root API route in String format.
     */
    fun getServerUrl() = "$SERVER_PROTOCOL://$SERVER_DOMAIN:$SERVER_PORT/api/v1"

    /**
     * The active JWT session token stored in the app's memory for usage between activities.
     */
    var authToken: Session? = null

}
