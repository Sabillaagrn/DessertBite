package lat.pam.dessertbite

data class OrderItem(
    val name: String,
    val price: Int,
    var quantity: Int = 1
)

object OrderManager {
    private val orders = mutableListOf<OrderItem>()

    fun addOrder(item: OrderItem) {
        val existingItem = orders.find { it.name == item.name }
        if (existingItem != null) {
            existingItem.quantity += 1
        } else {
            orders.add(item)
        }
    }

    fun getOrders(): List<OrderItem> {
        return orders.toList()
    }

    fun getTotalPrice(): Int {
        return orders.sumOf { it.price * it.quantity }
    }

    fun clearOrders() {
        orders.clear()
    }
}