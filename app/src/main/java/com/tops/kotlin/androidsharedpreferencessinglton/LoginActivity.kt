package com.tops.kotlin.androidsharedpreferencessinglton

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.tops.kotlin.androidsharedpreferencessinglton.databinding.ActivityLoginBinding
import com.tops.kotlin.androidsharedpreferencessinglton.preference.PrefManager

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    private lateinit var prefManager: PrefManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.txtCreateAccount.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }

        binding.loginBtn.setOnClickListener {
            val email = binding.edtEmailAddress.text.toString()
            val password = binding.edtPassword.text.toString()

            login(email, password)
        }
    }

    private fun login(email: String, password: String) {
        prefManager = PrefManager.getInstance(this)
        prefManager.loginUser(email, password)

        if (prefManager.isLogin()) {
            startActivity(Intent(this, HomeActivity::class.java))
            finish()
        }
    }
}