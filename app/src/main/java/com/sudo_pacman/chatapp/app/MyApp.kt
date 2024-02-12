package com.sudo_pacman.chatapp.app

import android.app.Application
import android.text.style.TtsSpan.TimeBuilder
import com.sudo_pacman.chatapp.data.MyShared
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MyApp : Application() {
    override fun onCreate() {
        super.onCreate()

        MyShared.init(this)
    }
}