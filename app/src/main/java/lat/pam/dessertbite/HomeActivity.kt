package lat.pam.dessertbite

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity

class HomeActivity : AppCompatActivity() {

    private lateinit var navHome: LinearLayout
    private lateinit var navOrder: LinearLayout
    private lateinit var navProfile: LinearLayout
    private lateinit var menuDonat: LinearLayout
    private lateinit var menuCookies: LinearLayout
    private lateinit var menuCheeseCake: LinearLayout
    private lateinit var menuIceCream: LinearLayout
    private lateinit var menuCroissants: LinearLayout
    private lateinit var menuLemonadeCake: LinearLayout
    private lateinit var menuMacarons: LinearLayout
    private lateinit var menuPancakes: LinearLayout
    private lateinit var menuPuddingManggo: LinearLayout
    private lateinit var menuMiniBrownies: LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        // Inisialisasi bottom navigation
        navHome = findViewById(R.id.navHome)
        navOrder = findViewById(R.id.navOrder)
        navProfile = findViewById(R.id.navProfile)

        // Inisialisasi menu items
        menuDonat = findViewById(R.id.menuDonat)
        menuCookies = findViewById(R.id.menuCookies)
        menuCheeseCake = findViewById(R.id.menuCheeseCake)
        menuIceCream = findViewById(R.id.menuIceCream)
        menuCroissants = findViewById(R.id.menuCroissants)
        menuLemonadeCake = findViewById(R.id.menuLemonadeCake)
        menuMacarons = findViewById(R.id.menuMacarons)
        menuPancakes = findViewById(R.id.menuPancakes)
        menuPuddingManggo = findViewById(R.id.menuPuddingManggo)
        menuMiniBrownies = findViewById(R.id.menuMiniBrownies)

        // Click listeners untuk menu
        menuDonat.setOnClickListener {
            showOrderDialog("Donat Coklat", 15000)
        }

        menuCookies.setOnClickListener {
            showOrderDialog("Cupcake Vanilla", 18000)
        }

        menuCheeseCake.setOnClickListener {
            showOrderDialog("Pudding Mangga", 12000)
        }

        menuIceCream.setOnClickListener {
            showOrderDialog("Ice Cream Strawberry", 20000)
        }

        menuCroissants.setOnClickListener {
            showOrderDialog("Ice Cream Strawberry", 20000)
        }

        menuLemonadeCake.setOnClickListener {
            showOrderDialog("Ice Cream Strawberry", 20000)
        }

        menuMacarons.setOnClickListener {
            showOrderDialog("Ice Cream Strawberry", 20000)
        }

        menuIceCream.setOnClickListener {
            showOrderDialog("Ice Cream Strawberry", 20000)
        }

        menuPancakes.setOnClickListener {
            showOrderDialog("Ice Cream Strawberry", 20000)
        }

        menuPuddingManggo.setOnClickListener {
            showOrderDialog("Ice Cream Strawberry", 20000)
        }

        menuMiniBrownies.setOnClickListener {
            showOrderDialog("Ice Cream Strawberry", 20000)
        }

        // Navigasi ke Order
        navOrder.setOnClickListener {
            val intent = Intent(this, OrderActivity::class.java)
            startActivity(intent)
        }

        // Navigasi ke Profile
        navProfile.setOnClickListener {
            val intent = Intent(this, ProfileActivity::class.java)
            startActivity(intent)
        }
    }

    private fun showOrderDialog(itemName: String, price: Int) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Konfirmasi Pesanan")
        builder.setMessage("Kamu telah memesan:\n\n$itemName\nHarga: Rp ${formatPrice(price)}\n\nPesanan akan ditambahkan ke keranjang.")

        builder.setPositiveButton("Ya, Pesan") { dialog, _ ->
            // Tambahkan ke order
            OrderManager.addOrder(OrderItem(itemName, price))

            // Tampilkan dialog sukses
            showSuccessDialog(itemName)
            dialog.dismiss()
        }

        builder.setNegativeButton("Batal") { dialog, _ ->
            dialog.dismiss()
        }

        val dialog = builder.create()
        dialog.show()
    }

    private fun showSuccessDialog(itemName: String) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Berhasil!")
        builder.setMessage("$itemName telah ditambahkan ke pesanan kamu.\n\nLihat di halaman Order untuk detail lengkap.")

        builder.setPositiveButton("Lihat Pesanan") { dialog, _ ->
            val intent = Intent(this, OrderActivity::class.java)
            startActivity(intent)
            dialog.dismiss()
        }

        builder.setNegativeButton("Pesan Lagi") { dialog, _ ->
            dialog.dismiss()
        }

        val dialog = builder.create()
        dialog.show()
    }

    private fun formatPrice(price: Int): String {
        return String.format("%,d", price).replace(',', '.')
    }
}