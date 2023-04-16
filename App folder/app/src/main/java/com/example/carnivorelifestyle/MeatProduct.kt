package com.example.carnivorelifestyle

import android.os.Parcelable

class MeatProduct {
    var category: String
    var name: String
    var origin: String
    var calories: Int
    var protein: Int
    var price: Int
    var image: Int

    constructor(category: String, name: String, origin: String, price: Int, calories: Int, protein: Int, image: Int) {
        this.category = category
        this.name = name
        this.origin = origin
        this.price = price
        this.calories = calories
        this.protein = protein
        this.image = image
    }
}

