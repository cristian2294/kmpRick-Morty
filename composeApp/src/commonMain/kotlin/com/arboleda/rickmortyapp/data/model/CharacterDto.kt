package com.arboleda.rickmortyapp.data.model

import com.arboleda.rickmortyapp.domain.model.Character
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CharacterDto(
    @SerialName("id")
    val id: Int,
    @SerialName("status")
    val status: String,
    @SerialName("image")
    val image: String,
    @SerialName("name")
    val name: String,
) {
    fun toDomain() =
        Character(
            id = id,
            isAlive = status.lowercase() == "alive",
            image = image,
            name = name,
        )
}
