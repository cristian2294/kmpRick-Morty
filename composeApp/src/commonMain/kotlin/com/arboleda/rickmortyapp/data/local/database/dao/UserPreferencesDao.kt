package com.arboleda.rickmortyapp.data.local.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.arboleda.rickmortyapp.data.local.database.entities.CharacterOfTheDayEntity

@Dao
interface UserPreferencesDao {
    @Query("SELECT * FROM CharacterOfTheDayEntity")
    suspend fun getCharacterOfTheDayEntity(): CharacterOfTheDayEntity?

    @Insert(entity = CharacterOfTheDayEntity::class, onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveCharacterOfTheDayEntity(characterOfTheDayEntity: CharacterOfTheDayEntity)
}
