package com.sudo_pacman.chatapp.utils

import android.util.Log


fun String.myLog(tag: String = "TTT") {
    Log.d(tag, this)
}