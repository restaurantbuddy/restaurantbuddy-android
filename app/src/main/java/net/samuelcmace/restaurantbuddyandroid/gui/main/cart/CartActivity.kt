package net.samuelcmace.restaurantbuddyandroid.gui.main.cart

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import net.samuelcmace.restaurantbuddyandroid.R

/**
 * Activity representing the user's cart.
 */
class CartActivity : AppCompatActivity() {

    /**
     * Method called by the Android API after the UI has been drawn.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart)
    }

}
