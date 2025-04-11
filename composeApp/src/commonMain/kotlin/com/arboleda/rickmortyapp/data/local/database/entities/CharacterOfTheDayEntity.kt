package com.arboleda.rickmortyapp.data.local.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.arboleda.rickmortyapp.domain.model.Character
import com.arboleda.rickmortyapp.domain.model.CharacterOfTheDay

@Entity(tableName = "CharacterOfTheDayEntity")
data class CharacterOfTheDayEntity(
    @PrimaryKey
    val id: Int,
    val isAlive: Boolean,
    val image: String,
    val name: String,
    val selectedDay: String,
) {
    fun toDomain() =
        CharacterOfTheDay(
            character =
                Character(
                    id = id,
                    isAlive = isAlive,
                    image = image,
                    name = name,
                ),
            selectedDay = selectedDay,
        )
}
