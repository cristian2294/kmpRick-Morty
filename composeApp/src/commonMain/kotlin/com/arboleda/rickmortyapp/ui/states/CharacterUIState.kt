package com.arboleda.rickmortyapp.ui.states

import com.arboleda.rickmortyapp.domain.model.Character

sealed class CharacterUIState {
    data object Loading : CharacterUIState()

    data class Success(
        val character: Character? = null,
    ) : CharacterUIState()

    data class Error(
        val message: String,
    ) : CharacterUIState()
}
