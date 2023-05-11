package net.samuelcmace.restaurantbuddyandroid.gui.main.cart

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import net.samuelcmace.restaurantbuddyandroid.R
import net.samuelcmace.restaurantbuddyandroid.database.entity.Item

/**
 * Adapter class for the cart item RecyclerView.
 *
 * @property context Context The context in which the adapter resides.
 * @property items ArrayList<Item> The list of items used by the adapter.
 * @constructor Initializes a new instance of the adapter.
 */
class CartItemAdapter(private val context: Context) : RecyclerView.Adapter<CartItemAdapter.CartItemHolder>() {

    private val items: ArrayList<Item> = ArrayList()

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
        TODO("Not yet implemented")
    }

    /**
     * Inner class representing the holder for the RecyclerView.
     *
     * @constructor Initializes a new instance of CartItemHolder.
     */
    inner class CartItemHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

}