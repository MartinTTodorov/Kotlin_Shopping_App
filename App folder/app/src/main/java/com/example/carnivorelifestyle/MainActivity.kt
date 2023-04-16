package com.example.carnivorelifestyle

import ProductsManager
import android.content.Intent
import android.os.Bundle
import android.os.Parcelable
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.bottomnavigation.BottomNavigationView
import java.io.Serializable

class MainActivity : AppCompatActivity() {


    val productsManager = ProductsManager;
    val products = arrayOf(
        MeatProduct("Beef", "Steak", "Natural bred cow from the valley of the Noord-Brabant",15, 360, 30, R.drawable.beef_steak),
        MeatProduct("Beef", "Burger", "origin",10, 320, 32, R.drawable.beef_burger),
        MeatProduct("Beef", "Roast", "origin",25,  290, 20, R.drawable.beef_roast),
        MeatProduct("Chicken", "Breast", "origin",10, 310, 27, R.drawable.chicken_breast),
        MeatProduct("Chicken", "Thigh", "origin",25, 325, 35, R.drawable.chicken_thigh),
        MeatProduct("Chicken", "Drumstick", "origin",15, 270, 32, R.drawable.chicken_drumstick),
        MeatProduct("Pork", "Chop", "origin", 250,12,  27, R.drawable.pork_chop),
        MeatProduct("Pork", "Belly", "origin", 370,10,  30, R.drawable.pork_belly),
        MeatProduct("Pork", "Loin", "origin", 320,20,  32, R.drawable.pork_loin))

    val beef = products.filter { it.category == "Beef" }.map { it.name }.toTypedArray()
    val chicken = products.filter { it.category == "Chicken" }.map { it.name }.toTypedArray()
    val pork = products.filter { it.category == "Pork" }.map { it.name }.toTypedArray()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.mainpage)


        val navigationView = findViewById<BottomNavigationView>(R.id.navigation)
        navigationView.setOnItemSelectedListener { menuItem: MenuItem ->
            when (menuItem.itemId) {
                R.id.navigation_home -> {

                    true
                }
                R.id.navigation_cart -> {
                    val cartIntent = Intent(this, CartActivity::class.java)
                    cartIntent.putExtra("price", productsManager.getTotalPrice())
                    cartIntent.putExtra("calories", productsManager.getTotalCalories())


                    startActivity(cartIntent)
                    true
                }
                R.id.navigation_info -> {
                    // Handle Info button click
                    true
                }
                else -> false
            }
        }

        val adapter = ArrayAdapter.createFromResource(this, R.array.meat_categories, android.R.layout.simple_spinner_item)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        findViewById<Spinner>(R.id.spinner).adapter = adapter

        findViewById<Spinner>(R.id.spinner).onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                val category = parent.getItemAtPosition(position).toString()
                displayMeat(category)
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // Do nothing
            }
        }
    }





    private fun displayMeat(category: String) {
        val meats: Array<MeatProduct> = when (category) {
            "Beef" -> products.filter { it.category == "Beef" }.toTypedArray()
            "Chicken" -> products.filter { it.category == "Chicken" }.toTypedArray()
            else -> products.filter { it.category == "Pork" }.toTypedArray()
        }

        val adapter = object : ArrayAdapter<MeatProduct>(this, R.layout.item_meat, meats) {
            override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
                val view = convertView ?: layoutInflater.inflate(R.layout.item_meat, parent, false)
                val meat = meats[position]

                view.findViewById<TextView>(R.id.name_text).text = meat.name
                view.findViewById<ImageView>(R.id.image).setImageResource(meat.image)
//                view.findViewById<TextView>(R.id.calories_text).text = "Calories: ${meat.calories}"
//                view.findViewById<TextView>(R.id.price_text).text = "Price: ${meat.price}"

                view.findViewById<Button>(R.id.add_button).setOnClickListener {
                    productsManager.addItemToCart(meat);
                    Log.d("asd", "${productsManager.getTotalPrice()}")
                }
                view.findViewById<Button>(R.id.info_button).setOnClickListener {
                    val intent = Intent(this@MainActivity, InfoActivity::class.java)
                    intent.putExtra("name", meat.name)
                    intent.putExtra("origin", meat.origin)
                    intent.putExtra("calories", meat.calories)
                    intent.putExtra("price", meat.price)
                    intent.putExtra("image", meat.image)
                    intent.putExtra("protein", meat.protein)
                    startActivity(intent)
                }



                return view
            }
        }

        findViewById<ListView>(R.id.meat_list).adapter = adapter



//    findViewById<ListView>(R.id.meat_list).adapter = adapter
    }



}