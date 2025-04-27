package com.arboleda.rickmortyapp.domain.model

import com.arboleda.rickmortyapp.data.local.database.entities.CharacterOfTheDayEntity
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

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
            species = character.species,
            gender = character.gender,
            origin = character.origin,
            episode = Json.encodeToString(character.episode),
        )
}
