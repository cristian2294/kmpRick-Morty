package com.arboleda.rickmortyapp.data.local.database

import android.content.Context
import androidx.room.Room
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import kotlinx.coroutines.Dispatchers

fun getDatabase(contex: Context): RickMortyDatabase {
    val dbFile = contex.getDatabasePath(DATABASE_NAME)
    return Room
        .databaseBuilder<RickMortyDatabase>(contex, dbFile.absolutePath)
        .setDriver(BundledSQLiteDriver())
        .setQueryCoroutineContext(Dispatchers.IO)
        .build()
}
