package com.arboleda.rickmortyapp.domain.usecases

import androidx.paging.PagingData
import com.arboleda.rickmortyapp.domain.model.Character
import com.arboleda.rickmortyapp.domain.repository.CharacterRepository
import kotlinx.coroutines.flow.Flow

class GetAllCharacters(
    private val characterRepository: CharacterRepository,
) {
    operator fun invoke(): Flow<PagingData<Character>> = characterRepository.getAllCharacter()
}
