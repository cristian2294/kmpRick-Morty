package com.arboleda.rickmortyapp.data.local.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.arboleda.rickmortyapp.domain.model.Character
import com.arboleda.rickmortyapp.domain.model.CharacterOfTheDay
import kotlinx.serialization.json.Json

@Entity(tableName = "CharacterOfTheDayEntity")
data class CharacterOfTheDayEntity(
    @PrimaryKey
    val id: Int,
    val isAlive: Boolean,
    val image: String,
    val name: String,
    val selectedDay: String,
    val species: String,
    val gender: String,
    val origin: String,
    val episode: String,
) {
    fun toDomain() =
        CharacterOfTheDay(
            character =
                Character(
                    id = id,
                    isAlive = isAlive,
                    image = image,
                    name = name,
                    species = species,
                    gender = gender,
                    origin = origin,
                    episode = Json.decodeFromString<List<String>>(episode),
                ),
            selectedDay = selectedDay,
        )
}
