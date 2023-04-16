package com.example.carnivorelifestyle

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView

class InfoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_info)

        val name = intent.getStringExtra("name")
        val calories = intent.getIntExtra("calories", 0)
        val price = intent.getIntExtra("price", 0)
        val origin = intent.getStringExtra("origin")
        val protein = intent.getIntExtra("protein", 0)
        val image = intent.getIntExtra("image", R.drawable.beef_steak)


        findViewById<TextView>(R.id.product_name_text).text = name
        findViewById<ImageView>(R.id.product_image).setImageResource(image)
        findViewById<TextView>(R.id.product_origin_text).text = "Origin: $origin"
        findViewById<TextView>(R.id.product_protein_text).text = "Protein: $protein g"
        findViewById<TextView>(R.id.product_calories_text).text = "Calories $calories"
        findViewById<TextView>(R.id.product_price_text).text = "Price: $price€ per kg"

        findViewById<Button>(R.id.back_button).setOnClickListener {
            finish()
        }
    }
}