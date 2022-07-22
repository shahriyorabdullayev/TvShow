package com.shahriyor.android_imperative

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class ImperativeApp: Application() {
    private val TAG = ImperativeApp::class.java.simpleName

    override fun onCreate() {
        super.onCreate()
    }
}