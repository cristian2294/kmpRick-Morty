package com.arboleda.rickmortyapp.domain.usecases

import com.arboleda.rickmortyapp.domain.model.Character
import com.arboleda.rickmortyapp.domain.repository.CharacterRepository
import kotlinx.coroutines.flow.Flow

class GetRandomCharacter(
    private val characterRepository: CharacterRepository,
) {
    suspend operator fun invoke(): Flow<Character> {
        val randomId = (1..826).random()
        return characterRepository.getCharacterForId(id = randomId)
    }
}
