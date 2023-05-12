package net.samuelcmace.restaurantbuddyandroid.gui.main.main

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
 * The adapter for the menu item RecyclerView.
 *
 * @property context The context in which the MenuItemAdapter resides.
 * @property allItems A list of objects to be displayed on the RecyclerView.
 * @property mCustomerService The instance of CustomerService used by the class.
 * @constructor Initializes a new instance of MenuItemAdapter.
 */
class MenuItemAdapter(private val context: Context) : RecyclerView.Adapter<MenuItemAdapter.MenuItemHolder>() {

    private var allItems: List<Item> = ArrayList()
    private lateinit var mCustomerService: CustomerService

    /**
     * Method called by the Android API after the view has been created.
     *
     * @param parent ViewGroup The parent view for the RecyclerView.
     * @param viewType Int The view type to be created.
     * @return CartItemHolder The new inflated holder object.
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuItemHolder {

        this.mCustomerService = CustomerService(this.context)

        val itemView = LayoutInflater.from(context).inflate(R.layout.item_menu, parent, false)
        return MenuItemHolder(itemView)
    }

    /**
     * Gets the number of items stored in the RecyclerView.
     *
     * @return Int The number of items stored in the RecyclerView.
     */
    override fun getItemCount() = allItems.count()

    /**
     * Method called by the Android API to bind an individual item to the RecyclerView.
     *
     * @param holder The item holder in question.
     * @param position The index that corresponds to the item in question.
     */
    override fun onBindViewHolder(holder: MenuItemHolder, position: Int) {

        holder.itemView.apply {
            val tvProductName = findViewById<TextView>(R.id.tvProductName)
            val tvProductDescription = findViewById<TextView>(R.id.tvProductDescription)
            val tvProductPrice = findViewById<TextView>(R.id.tvProductPrice)

            tvProductName.text = allItems[position].name
            tvProductDescription.text = allItems[position].description
            tvProductPrice.text = allItems[position].cost.toString()
        }

        holder.itemView.setOnClickListener {

            AlertDialog.Builder(this.context)
                .setTitle("Item Confirmation")
                .setMessage("Would you like a ${allItems[position].name}?")
                .setPositiveButton("Yes") { _, _ ->
                    this.mCustomerService.addItemToCart(allItems[position])
                    Toast.makeText(
                        this.context,
                        "You added the ${allItems[position].name} to your menu.",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                .setNegativeButton("No") { _, _ ->
                    Toast.makeText(
                        this.context,
                        "You refrained from adding the ${allItems[position].name} to your menu.",
                        Toast.LENGTH_SHORT
                    ).show()
                }.create().show()

        }

    }

    /**
     * Method to set the items to be shown on the RecyclerView.
     *
     * @param items The items in question.
     */
    fun setAllItems(items: List<Item>) {
        this.allItems = items
        notifyDataSetChanged()
    }

    /**
     * An inner class representing the holder for the RecyclerView.
     *
     * @constructor Initializes a new instance of MenuItemHolder.
     */
    inner class MenuItemHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

}
