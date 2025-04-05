package com.arboleda.rickmortyapp.domain.model

data class CharacterWrapper(
    val info: Info,
    val results: List<Character>,
)
