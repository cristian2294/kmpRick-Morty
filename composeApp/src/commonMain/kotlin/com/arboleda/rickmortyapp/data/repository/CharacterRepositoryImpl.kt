package com.arboleda.rickmortyapp.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.arboleda.rickmortyapp.data.local.database.RickMortyDatabase
import com.arboleda.rickmortyapp.data.paging.CharacterPagingSource
import com.arboleda.rickmortyapp.data.remote.ApiService
import com.arboleda.rickmortyapp.domain.model.Character
import com.arboleda.rickmortyapp.domain.model.CharacterOfTheDay
import com.arboleda.rickmortyapp.domain.repository.CharacterRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class CharacterRepositoryImpl(
    private val apiService: ApiService,
    private val characterPagingSource: CharacterPagingSource,
    private val database: RickMortyDatabase,
) : CharacterRepository {
    companion object {
        const val MAX_ITEMS = 20
        const val PREFETCH_DISTANCE = 5
    }

    override suspend fun getCharacterForId(id: Int): Flow<Character> =
        flow {
            emit(apiService.getCharacterForId(id).toDomain())
        }

    override fun getAllCharacter(): Flow<PagingData<Character>> =
        Pager(
            config =
                PagingConfig(
                    pageSize = MAX_ITEMS,
                    prefetchDistance = PREFETCH_DISTANCE,
                ),
            pagingSourceFactory = { characterPagingSource },
        ).flow

    override suspend fun getCharacterDb(): CharacterOfTheDay? = database.preferencesDao().getCharacterOfTheDayEntity()?.toDomain()

    override suspend fun saveCharacterOfTheDay(characterOfTheDay: CharacterOfTheDay) {
        database.preferencesDao().saveCharacterOfTheDayEntity(characterOfTheDay.toEntity())
    }
}
