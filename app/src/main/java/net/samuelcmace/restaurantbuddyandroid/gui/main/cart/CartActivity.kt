package net.samuelcmace.restaurantbuddyandroid.gui.main.cart

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import net.samuelcmace.restaurantbuddyandroid.R
import net.samuelcmace.restaurantbuddyandroid.service.CustomerService

/**
 * Activity representing the user's cart.
 *
 * @property mCustomerService The instance of CustomerService used by the activity.
 * @property rvCart The RecyclerView object used to view the items in the cart (stored in the database).
 * @property mCartItemAdapter The adapter object for the RecyclerView.
 */
class CartActivity : AppCompatActivity() {

    private lateinit var mCustomerService: CustomerService

    private lateinit var rvCart: RecyclerView
    private lateinit var mCartItemAdapter: CartItemAdapter

    /**
     * Method called by the Android API after the UI has been drawn.
     *
     * @param savedInstanceState The object to reference the previously-created activity if it is being recreated.
     */
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart)

        this.mCustomerService = CustomerService(this)

        this.rvCart = findViewById(R.id.rvCart)
        this.rvCart.layoutManager = LinearLayoutManager(this)
        this.mCartItemAdapter = CartItemAdapter(this)

        loadCart()
        refreshRecyclerView()

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

                AlertDialog.Builder(this)
                    .setTitle("Item Confirmation")
                    .setMessage("Would you like to clear the shopping cart of all active items?")
                    .setPositiveButton("Yes") { _, _ ->
                        clearCart()
                        Toast.makeText(
                            this,
                            "You cleared your shopping cart of all items.",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                    .setNegativeButton("No") { _, _ ->
                        Toast.makeText(
                            this,
                            "You refrained from clearing your shopping cart of all items.",
                            Toast.LENGTH_SHORT
                        ).show()
                    }.create().show()

            }

            R.id.itBack -> {
                finish()
            }

        }
        return super.onOptionsItemSelected(item)
    }

    /**
     * Method to refresh the recycler view with updated data.
     */
    private fun refreshRecyclerView() {
        this.rvCart.adapter = this.mCartItemAdapter
    }

    /**
     * Method called to load all the items from the cart in the database.
     */
    private fun loadCart() {
        this.mCartItemAdapter.setCartItems(this.mCustomerService.getCartItems())
    }

    /**
     * Method called to clear the cart of all active items. This method will clear all items stored in the Item table
     * in the database.
     */
    private fun clearCart() {
        this.mCustomerService.clearShoppingCart()
        loadCart()
    }

}
