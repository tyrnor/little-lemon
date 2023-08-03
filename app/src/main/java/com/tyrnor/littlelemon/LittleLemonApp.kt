package com.tyrnor.littlelemon

import android.app.Application
import android.content.Context
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class LittleLemonApp: Application() {
    init {
        instance = this
    }

    companion object {
        private var instance: LittleLemonApp? = null

        fun applicationContext() : Context {
            return instance!!.applicationContext
        }
    }

    override fun onCreate() {
        super.onCreate()
        // initialize for any

        // Use ApplicationContext.
        // example: SharedPreferences etc...
        val context: Context = LittleLemonApp.applicationContext()
    }
}