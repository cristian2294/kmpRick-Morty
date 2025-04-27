package com.arboleda.rickmortyapp.domain.usecases.character

data class CharacterModule(
    val getCharacterForIdUC: GetRandomCharacter,
    val getAllCharactersUC: GetAllCharacters,
)
