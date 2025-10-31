package lat.pam.dessertbite

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class DeliveryActivity : AppCompatActivity() {

    private lateinit var etNama: EditText
    private lateinit var etAlamat: EditText
    private lateinit var etAlamatLengkap: EditText
    private lateinit var btnOrderKirim: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_delivery)

        etNama = findViewById(R.id.etNama)
        etAlamat = findViewById(R.id.etAlamat)
        etAlamatLengkap = findViewById(R.id.etAlamatLengkap)
        btnOrderKirim = findViewById(R.id.btnOrderKirim)

        btnOrderKirim.setOnClickListener {
            val nama = etNama.text.toString()
            val alamat = etAlamat.text.toString()
            val alamatLengkap = etAlamatLengkap.text.toString()

            if (nama.isEmpty() || alamat.isEmpty() || alamatLengkap.isEmpty()) {
                Toast.makeText(this, "Mohon isi semua field", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Pesanan berhasil dikirim!", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, ProfileActivity::class.java)
                startActivity(intent)
                finish()
            }
        }
    }
}