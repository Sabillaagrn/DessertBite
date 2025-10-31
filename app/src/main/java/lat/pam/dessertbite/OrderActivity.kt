package lat.pam.dessertbite

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class OrderActivity : AppCompatActivity() {

    private lateinit var btnKirim: Button
    private lateinit var navHome: LinearLayout
    private lateinit var navOrder: LinearLayout
    private lateinit var navProfile: LinearLayout
    private lateinit var orderListContainer: LinearLayout
    private lateinit var tvEmptyOrder: TextView
    private lateinit var tvTotalPrice: TextView
    private lateinit var totalPriceContainer: LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order)

        btnKirim = findViewById(R.id.btnKirim)
        navHome = findViewById(R.id.navHome)
        navOrder = findViewById(R.id.navOrder)
        navProfile = findViewById(R.id.navProfile)
        orderListContainer = findViewById(R.id.orderListContainer)
        tvEmptyOrder = findViewById(R.id.tvEmptyOrder)
        tvTotalPrice = findViewById(R.id.tvTotalPrice)
        totalPriceContainer = findViewById(R.id.totalPriceContainer)

        // Load orders
        loadOrders()

        // Tombol Kirim ke halaman Delivery
        btnKirim.setOnClickListener {
            val intent = Intent(this, DeliveryActivity::class.java)
            startActivity(intent)
        }

        // Navigasi ke Home
        navHome.setOnClickListener {
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
        }

        // Navigasi ke Profile
        navProfile.setOnClickListener {
            val intent = Intent(this, ProfileActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onResume() {
        super.onResume()
        loadOrders()
    }

    private fun loadOrders() {
        val orders = OrderManager.getOrders()

        if (orders.isEmpty()) {
            tvEmptyOrder.visibility = View.VISIBLE
            totalPriceContainer.visibility = View.GONE
            btnKirim.isEnabled = false
            btnKirim.alpha = 0.5f
        } else {
            tvEmptyOrder.visibility = View.GONE
            totalPriceContainer.visibility = View.VISIBLE
            btnKirim.isEnabled = true
            btnKirim.alpha = 1.0f

            // Clear previous items except empty text
            orderListContainer.removeAllViews()

            // Add each order item
            orders.forEach { order ->
                val orderView = createOrderItemView(order)
                orderListContainer.addView(orderView)
            }

            // Update total price
            val total = OrderManager.getTotalPrice()
            tvTotalPrice.text = "Rp ${formatPrice(total)}"
        }
    }

    private fun createOrderItemView(order: OrderItem): View {
        val itemView = LinearLayout(this).apply {
            orientation = LinearLayout.HORIZONTAL
            setPadding(0, 16, 0, 16)
            layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
        }

        val textContainer = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            layoutParams = LinearLayout.LayoutParams(
                0,
                LinearLayout.LayoutParams.WRAP_CONTENT,
                1f
            )
        }

        val nameText = TextView(this).apply {
            text = order.name
            textSize = 16f
            setTextColor(resources.getColor(android.R.color.black, null))
            setTypeface(null, android.graphics.Typeface.BOLD)
        }

        val detailText = TextView(this).apply {
            text = "${order.quantity}x • Rp ${formatPrice(order.price)}"
            textSize = 14f
            setTextColor(resources.getColor(android.R.color.darker_gray, null))
        }

        val totalText = TextView(this).apply {
            text = "Rp ${formatPrice(order.price * order.quantity)}"
            textSize = 16f
            setTextColor(resources.getColor(android.R.color.holo_orange_dark, null))
            setTypeface(null, android.graphics.Typeface.BOLD)
        }

        textContainer.addView(nameText)
        textContainer.addView(detailText)

        itemView.addView(textContainer)
        itemView.addView(totalText)

        return itemView
    }

    private fun formatPrice(price: Int): String {
        return String.format("%,d", price).replace(',', '.')
    }
}