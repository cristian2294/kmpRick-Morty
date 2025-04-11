package com.arboleda.rickmortyapp.domain.usecases

import com.arboleda.rickmortyapp.domain.model.Character
import com.arboleda.rickmortyapp.domain.model.CharacterOfTheDay
import com.arboleda.rickmortyapp.domain.repository.CharacterRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

class GetRandomCharacter(
    private val characterRepository: CharacterRepository,
) {
    operator fun invoke(): Flow<Character> =
        flow {
            val characterOfTheDay = characterRepository.getCharacterDb()
            val selectedDay = getCurrentDayOfTheYear()

            if (characterOfTheDay != null && characterOfTheDay.selectedDay == selectedDay) {
                emit(characterOfTheDay.character)
            } else {
                val character = generateRandomCharacter()
                characterRepository.saveCharacterOfTheDay(
                    CharacterOfTheDay(
                        character = character,
                        selectedDay = selectedDay,
                    ),
                )
                emit(character)
            }
        }

    private suspend fun generateRandomCharacter(): Character {
        val randomId = (1..826).random()
        return characterRepository.getCharacterForId(id = randomId).first()
    }

    private fun getCurrentDayOfTheYear(): String {
        val instant = Clock.System.now()
        val localTime = instant.toLocalDateTime(TimeZone.currentSystemDefault())
        return "${localTime.year}${localTime.dayOfYear}"
    }
}
