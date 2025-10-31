package lat.pam.dessertbite

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class ProfileActivity : AppCompatActivity() {

    private lateinit var btnKeluar: Button
    private lateinit var navHome: LinearLayout
    private lateinit var navOrder: LinearLayout
    private lateinit var navProfile: LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        btnKeluar = findViewById(R.id.btnKeluar)
        navHome = findViewById(R.id.navHome)
        navOrder = findViewById(R.id.navOrder)
        navProfile = findViewById(R.id.navProfile)

        // Tombol Keluar (Logout)
        btnKeluar.setOnClickListener {
            Toast.makeText(this, "Anda telah selesai melakukan transaksi.", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, HomeActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
            finish()
        }
    }
}