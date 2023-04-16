import com.example.carnivorelifestyle.MeatProduct
import java.io.Serializable

object ProductsManager {
    private val items = mutableListOf<MeatProduct>()

    fun addItemToCart(item: MeatProduct) {
        items.add(item)
    }

    fun removeItemFromCart(item: MeatProduct) {
        items.remove(item)
    }

    fun getItems(): List<MeatProduct> {
        return items.toList()
    }

    fun finishOrder(){
        items.clear();
    }

    fun getTotalPrice(): Int {
        var totalPrice = 0
        for (item in items) {
            totalPrice += item.price
        }
        return totalPrice
    }

    fun getTotalCalories(): Int {
        var totalCalories = 0
        for (item in items) {
            totalCalories += item.calories
        }
        return totalCalories
    }
}
