package com.tops.kotlin.androidsharedpreferencessinglton

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.tops.kotlin.androidsharedpreferencessinglton.databinding.ActivityRegisterBinding
import com.tops.kotlin.androidsharedpreferencessinglton.preference.PrefManager

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding

    private lateinit var prefManager: PrefManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.createAccountBtn.setOnClickListener {
            val name = binding.edtName.text.toString().trim()
            val email = binding.edtEmailAddress.text.toString().trim()
            val password = binding.edtPassword.text.toString().trim()

            register(name, email, password)
        }

        binding.txtLogin.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }
    }

    private fun register(name: String, email: String, password: String) {
        prefManager = PrefManager.getInstance(this)
        prefManager.registerUser(name, email, password)

        startActivity(Intent(this, HomeActivity::class.java))
        finish()
    }
}