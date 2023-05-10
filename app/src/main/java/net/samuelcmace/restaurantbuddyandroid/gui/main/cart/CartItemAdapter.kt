package net.samuelcmace.restaurantbuddyandroid.gui.main.cart

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import net.samuelcmace.restaurantbuddyandroid.R
import net.samuelcmace.restaurantbuddyandroid.database.entity.Item

class CartItemAdapter(private val context: Context) : RecyclerView.Adapter<CartItemAdapter.CartItemHolder>() {

    private val items: ArrayList<Item> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartItemHolder {
        val itemView = LayoutInflater.from(context).inflate(R.layout.item_cart, parent, false)
        return CartItemHolder(itemView)
    }

    override fun getItemCount() = items.count()

    override fun onBindViewHolder(holder: CartItemHolder, position: Int) {
        TODO("Not yet implemented")
    }

    inner class CartItemHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }

}