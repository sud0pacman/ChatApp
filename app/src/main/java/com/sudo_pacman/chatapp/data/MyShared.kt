package com.sudo_pacman.chatapp.data

import android.content.Context
import android.content.SharedPreferences

object MyShared {
    private lateinit var pref: SharedPreferences

    fun init(context: Context) {
        if (!(::pref.isInitialized)) pref = context.getSharedPreferences("CHAT", Context.MODE_PRIVATE)
    }

    fun setPhone(phone: String) = pref.edit().putString("PHONE", phone)

    fun getPhone(): String = pref.getString("PHONE", "") ?: ""
}