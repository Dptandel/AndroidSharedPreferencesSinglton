package com.tops.kotlin.androidsharedpreferencessinglton.preference

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import android.content.SharedPreferences.Editor
import android.widget.Toast
import com.tops.kotlin.androidsharedpreferencessinglton.model.User

class PrefManager(private var context: Context) {

    private var preferences: SharedPreferences
    private var editor: Editor
    private var PREF_NAME = "session"

    init {
        preferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        editor = preferences.edit()
    }

    companion object {
        private var instance: PrefManager? = null
        private val KEY_NAME = "name"
        private val KEY_EMAIL = "email"
        private val KEY_PASSWORD = "password"
        private val IS_LOGIN = "isLogin"

        fun getInstance(context: Context): PrefManager {
            if (instance == null) {
                instance = PrefManager(context)
            }
            return instance!!
        }
    }

    fun registerUser(name: String, email: String, password: String) {
        editor.putString(KEY_NAME, name)
        editor.putString(KEY_EMAIL, email)
        editor.putString(KEY_PASSWORD, password)
        editor.putBoolean(IS_LOGIN, true)
        editor.commit()

        Toast.makeText(context, "User Registered Successfully", Toast.LENGTH_SHORT).show()
    }

    fun getUser(): User? {
        val name = preferences.getString(KEY_NAME, null)
        val email = preferences.getString(KEY_EMAIL, null)
        val password = preferences.getString(KEY_PASSWORD, null)

        // Check if the values are present before creating the User object
        return if (name != null && email != null && password != null) {
            User(name, email, password)
        } else {
            null
        }
    }

    fun isLogin(): Boolean {
        return preferences.getBoolean(IS_LOGIN, false)
    }

    fun logout() {
        editor.remove(IS_LOGIN) // Remove the login status key
        editor.commit()
    }

    fun loginUser(email: String, password: String) {
        val storedUser = getUser()

        if (storedUser != null && storedUser.email == email && storedUser.password == password) {
            editor.putBoolean(IS_LOGIN, true)
            editor.commit()
            Toast.makeText(context, "Login Successful", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(context, "Invalid Credentials", Toast.LENGTH_SHORT).show()
        }
    }
}