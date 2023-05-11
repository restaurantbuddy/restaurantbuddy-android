package net.samuelcmace.restaurantbuddyandroid.gui.main.cart

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import net.samuelcmace.restaurantbuddyandroid.R

/**
 * Activity representing the user's cart.
 */
class CartActivity : AppCompatActivity() {

    /**
     * Method called by the Android API after the UI has been drawn.
     *
     * @param savedInstanceState The object to reference the previously-created activity if it is being recreated.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart)

        loadCart()
    }

    /**
     * Method called by the Android API to load the menu bar for the activity.
     *
     * @param menu The menu bar to be inflated.
     */
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_cart, menu)
        return super.onCreateOptionsMenu(menu)
    }

    /**
     * Method called by the Android API after a menu item has been selected.
     *
     * @param item The menu item selected.
     */
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {

            R.id.itClearCart -> {
                clearCart()
            }

            R.id.itBack -> {
                finish()
            }

        }
        return super.onOptionsItemSelected(item)
    }

    /**
     * Method called to load all the items from the cart in the database.
     */
    private fun loadCart() {
        Toast.makeText(this, "Thanks for stopping by, but this method has not yet been implemented.", Toast.LENGTH_LONG)
            .show()
        TODO("Implement the loadCart method in the CartActivity")
    }

    /**
     * Method called to clear the cart of all active items. This method will clear all items stored in the Item table
     * in the database.
     */
    private fun clearCart() {
        Toast.makeText(this, "Thanks for stopping by, but this method has not yet been implemented.", Toast.LENGTH_LONG)
            .show()
        TODO("Implement the clearCart method in the CartActivity")
    }

}
