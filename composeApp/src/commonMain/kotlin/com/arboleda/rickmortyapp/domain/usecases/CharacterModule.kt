package com.arboleda.rickmortyapp.domain.usecases

data class CharacterModule(
    val getCharacterForIdUC: GetRandomCharacter,
    val getAllCharactersUC: GetAllCharacters,
)
