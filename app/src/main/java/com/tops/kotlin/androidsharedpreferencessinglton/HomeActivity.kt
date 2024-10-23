package com.tops.kotlin.androidsharedpreferencessinglton

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.tops.kotlin.androidsharedpreferencessinglton.databinding.ActivityHomeBinding
import com.tops.kotlin.androidsharedpreferencessinglton.preference.PrefManager

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding
    private lateinit var prefManager: PrefManager

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        prefManager = PrefManager(this)

        if (prefManager.isLogin()) {
            val user = prefManager.getUser()

            if (user != null) {
                binding.tvUserDetails.text = """
                    Name : ${user.name}
                    Email : ${user.email}
                """.trimIndent()
            } else {
                startActivity(Intent(this, LoginActivity::class.java))
                Toast.makeText(this, "User is not logged in", Toast.LENGTH_SHORT).show()
            }
        }

        binding.btnLogout.setOnClickListener {
            prefManager.logout()
            Toast.makeText(this, "Logout successful", Toast.LENGTH_SHORT).show()
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }
    }
}