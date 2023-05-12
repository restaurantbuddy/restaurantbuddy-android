package net.samuelcmace.restaurantbuddyandroid.gui.main.cart

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import net.samuelcmace.restaurantbuddyandroid.R
import net.samuelcmace.restaurantbuddyandroid.database.entity.Item
import net.samuelcmace.restaurantbuddyandroid.service.CustomerService

/**
 * Adapter class for the cart item RecyclerView.
 *
 * @property context Context The context in which the adapter resides.
 * @property items ArrayList<Item> The list of items used by the adapter.
 * @constructor Initializes a new instance of the adapter.
 */
class CartItemAdapter(private val context: Context) : RecyclerView.Adapter<CartItemAdapter.CartItemHolder>() {

    private lateinit var mCustomerService: CustomerService
    private var items: List<Item> = ArrayList()

    /**
     * Method called by the Android API after the view has been created.
     *
     * @param parent ViewGroup The parent view for the RecyclerView.
     * @param viewType Int The view type to be created.
     * @return CartItemHolder The new inflated holder object.
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartItemHolder {
        val itemView = LayoutInflater.from(context).inflate(R.layout.item_cart, parent, false)
        return CartItemHolder(itemView)
    }

    /**
     * Gets the number of items stored in the RecyclerView.
     *
     * @return Int The number of items stored in the RecyclerView.
     */
    override fun getItemCount() = items.count()

    /**
     * Method called by the Android API to bind an individual item to the RecyclerView.
     *
     * @param holder The item holder in question.
     * @param position The index that corresponds to the item in question.
     */
    override fun onBindViewHolder(holder: CartItemHolder, position: Int) {

        holder.itemView.apply {
            val tvProductName = findViewById<TextView>(R.id.tvProductName)
            val tvProductPrice = findViewById<TextView>(R.id.tvProductPrice)

            tvProductName.text = items[position].name
            tvProductPrice.text = items[position].cost.toString()
        }

        holder.itemView.setOnClickListener {

            AlertDialog.Builder(this.context)
                .setTitle("Deletion Confirmation")
                .setMessage("Would you like to delete the ${items[position].name} from your cart?")
                .setPositiveButton("Yes") { _, _ ->
                    this.mCustomerService.removeItemFromCart(items[position])
                    notifyItemChanged(position)
                    Toast.makeText(
                        this.context,
                        "You deleted the ${items[position].name} from your shopping cart.",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                .setNegativeButton("No") { _, _ ->
                    Toast.makeText(
                        this.context,
                        "You refrained from deleting the ${items[position].name} from your shopping cart.",
                        Toast.LENGTH_SHORT
                    ).show()
                }.create().show()

        }

    }

    /**
     * Method called to set the active items in the cart.
     *
     * @param items The items to be assigned to the adapter upon initialization.
     */
    fun setCartItems(items: List<Item>) {
        this.items = items
        notifyDataSetChanged()
    }

    /**
     * Inner class representing the holder for the RecyclerView.
     *
     * @constructor Initializes a new instance of CartItemHolder.
     */
    inner class CartItemHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

}