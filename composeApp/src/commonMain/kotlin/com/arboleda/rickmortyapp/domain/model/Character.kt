package com.arboleda.rickmortyapp.domain.model

data class Character(
    val id: Int,
    val isAlive: Boolean,
    val image: String,
    val name: String,
)
