package com.arboleda.rickmortyapp.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class Character(
    val id: Int,
    val isAlive: Boolean,
    val image: String,
    val name: String,
    val species: String,
    val gender: String,
    val origin: String,
    val episode: List<String>,
)
