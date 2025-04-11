package com.arboleda.rickmortyapp.domain.model

import com.arboleda.rickmortyapp.data.local.database.entities.CharacterOfTheDayEntity

data class CharacterOfTheDay(
    val character: Character,
    val selectedDay: String,
) {
    fun toEntity(): CharacterOfTheDayEntity =
        CharacterOfTheDayEntity(
            id = character.id,
            isAlive = character.isAlive,
            image = character.image,
            name = character.name,
            selectedDay = selectedDay,
        )
}
