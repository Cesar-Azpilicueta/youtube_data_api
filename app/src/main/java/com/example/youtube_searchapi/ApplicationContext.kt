package com.example.youtube_searchapi

import android.app.Application
import android.content.Context

class ApplicationContext : Application() {
    init{
        instance = this
    }

    companion object {
        lateinit var instance: ApplicationContext
        fun getContext() : Context {
            return instance.applicationContext
        }
    }
}