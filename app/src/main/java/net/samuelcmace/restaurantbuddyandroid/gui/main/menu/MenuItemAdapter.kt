package net.samuelcmace.restaurantbuddyandroid.gui.main.menu

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import net.samuelcmace.restaurantbuddyandroid.R
import net.samuelcmace.restaurantbuddyandroid.database.entity.Item

class MenuItemAdapter(private val context: Context) : RecyclerView.Adapter<MenuItemAdapter.MenuItemHolder>() {

    private val items: ArrayList<Item> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuItemHolder {
        val itemView = LayoutInflater.from(context).inflate(R.layout.item_menu, parent, false)
        return MenuItemHolder(itemView)
    }

    override fun getItemCount() = items.count()

    override fun onBindViewHolder(holder: MenuItemHolder, position: Int) {
        TODO("Not yet implemented")
    }

    inner class MenuItemHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

}