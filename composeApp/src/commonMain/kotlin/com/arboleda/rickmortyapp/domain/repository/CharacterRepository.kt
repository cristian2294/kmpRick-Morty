package com.arboleda.rickmortyapp.domain.repository

import androidx.paging.PagingData
import com.arboleda.rickmortyapp.domain.model.Character
import com.arboleda.rickmortyapp.domain.model.CharacterOfTheDay
import kotlinx.coroutines.flow.Flow

interface CharacterRepository {
    suspend fun getCharacterForId(id: Int): Flow<Character>

    fun getAllCharacter(): Flow<PagingData<Character>>

    suspend fun getCharacterDb(): CharacterOfTheDay?

    suspend fun saveCharacterOfTheDay(characterOfTheDay: CharacterOfTheDay)
}
