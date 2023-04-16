package com.example.carnivorelifestyle

import ProductsManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Button
import android.widget.ImageView
import android.widget.ListView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class CartActivity : AppCompatActivity() {

    private lateinit var listView: ListView
    private lateinit var totalPriceTextView: TextView
    private lateinit var totalCaloriesTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart)

        listView = findViewById(R.id.cart_list)
        totalPriceTextView = findViewById(R.id.total_price_text)
        totalCaloriesTextView = findViewById(R.id.total_calories_text)

        listView.adapter = CartListAdapter()

        findViewById<Button>(R.id.btn_back).setOnClickListener {
            finish()
        }

        findViewById<Button>(R.id.finish_order_button).setOnClickListener {
            ProductsManager.finishOrder()
            (listView.adapter as CartListAdapter).notifyDataSetChanged()
            updateTotals()



        }

        updateTotals()
    }

    private fun updateTotals() {
        totalPriceTextView.text = "Total price: ${ProductsManager.getTotalPrice()} €"
        totalCaloriesTextView.text = "Total calories: ${ProductsManager.getTotalCalories()}"
    }

    inner class CartListAdapter : BaseAdapter() {
        override fun getCount(): Int {
            return ProductsManager.getItems().size
        }

        override fun getItem(position: Int): Any {
            return ProductsManager.getItems()[position]
        }

        override fun getItemId(position: Int): Long {
            return position.toLong()
        }

        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
            val view: View
            val viewHolder: ViewHolder

            if (convertView == null) {
                view = LayoutInflater.from(this@CartActivity).inflate(R.layout.cart_product, null)
                viewHolder = ViewHolder()
                viewHolder.productNameTextView = view.findViewById(R.id.product_name_text)
                viewHolder.productPriceTextView = view.findViewById(R.id.product_price_text)
                viewHolder.removeButton = view.findViewById(R.id.remove_button)
                viewHolder.productImage = view.findViewById(R.id.product_image)
                view.tag = viewHolder
            } else {
                view = convertView
                viewHolder = view.tag as ViewHolder
            }

            val product = getItem(position) as MeatProduct
            viewHolder.productNameTextView.text = product.name
            viewHolder.productPriceTextView.text = "${product.price} €"
            viewHolder.productImage.setImageResource(product.image)

            viewHolder.removeButton.setOnClickListener {
                ProductsManager.removeItemFromCart(product)
                updateTotals()
                notifyDataSetChanged()
            }

            return view
        }
    }

    private class ViewHolder {
        lateinit var productNameTextView: TextView
        lateinit var productPriceTextView: TextView
        lateinit var productImage: ImageView
        lateinit var removeButton: Button
    }
}
