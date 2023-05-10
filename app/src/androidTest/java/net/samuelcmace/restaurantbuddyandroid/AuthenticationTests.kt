package net.samuelcmace.restaurantbuddyandroid

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import kotlinx.coroutines.runBlocking
import net.samuelcmace.restaurantbuddyandroid.service.AuthenticationService
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class AuthenticationTests {

    @Test
    fun loginTest() {
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext

        val authenticationService = AuthenticationService(appContext)

        runBlocking {
            authenticationService.login("test", "Password1234")
        }

        println(AppConfig.authToken.toString())

        assertNotEquals(AppConfig.authToken, null)
    }

}
