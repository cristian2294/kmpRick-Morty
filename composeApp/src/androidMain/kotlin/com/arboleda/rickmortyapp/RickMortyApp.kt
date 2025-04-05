package com.arboleda.rickmortyapp

import android.app.Application
import com.arboleda.rickmortyapp.di.iniKoin
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger

class RickMortyApp : Application() {
    override fun onCreate() {
        super.onCreate()
        iniKoin {
            androidLogger()
            androidContext(this@RickMortyApp)
        }
    }
}
