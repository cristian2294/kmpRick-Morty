package com.arboleda.rickmortyapp.data.local.database

import androidx.room.ConstructedBy
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.RoomDatabaseConstructor
import com.arboleda.rickmortyapp.data.local.database.dao.UserPreferencesDao
import com.arboleda.rickmortyapp.data.local.database.entities.CharacterOfTheDayEntity

const val DATABASE_NAME = "rick_morty_database.db"

expect object RickMortyCTor : RoomDatabaseConstructor<RickMortyDatabase>

@Database(
    entities = [
        CharacterOfTheDayEntity::class,
    ],
    version = 1,
)
@ConstructedBy(RickMortyCTor::class)
abstract class RickMortyDatabase : RoomDatabase() {
    abstract fun preferencesDao(): UserPreferencesDao
}
