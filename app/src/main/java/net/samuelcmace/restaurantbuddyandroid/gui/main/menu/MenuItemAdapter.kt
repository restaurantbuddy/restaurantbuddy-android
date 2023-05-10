package net.samuelcmace.restaurantbuddyandroid.gui.main.menu

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import net.samuelcmace.restaurantbuddyandroid.R
import net.samuelcmace.restaurantbuddyandroid.database.entity.Item

class MenuItemAdapter(private val context: Context) : RecyclerView.Adapter<MenuItemAdapter.MenuItemHolder>() {

    private var allItems: List<Item> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuItemHolder {
        val itemView = LayoutInflater.from(context).inflate(R.layout.item_menu, parent, false)
        return MenuItemHolder(itemView)
    }

    override fun getItemCount() = allItems.count()

    override fun onBindViewHolder(holder: MenuItemHolder, position: Int) {
        holder.itemView.apply {
            val tvProductName = findViewById<TextView>(R.id.tvProductName)
            val tvProductDescription = findViewById<TextView>(R.id.tvProductDescription)
            val tvProductPrice = findViewById<TextView>(R.id.tvProductPrice)

            tvProductName.text = allItems[position].name
            tvProductDescription.text = allItems[position].description
            tvProductPrice.text = allItems[position].cost.toString()
        }
    }

    fun setAllItems(items: List<Item>) {
        this.allItems = items
        notifyDataSetChanged()
    }


    inner class MenuItemHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

}