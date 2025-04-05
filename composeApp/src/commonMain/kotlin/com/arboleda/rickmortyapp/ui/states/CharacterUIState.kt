package com.arboleda.rickmortyapp.ui.states

import androidx.paging.PagingData
import com.arboleda.rickmortyapp.domain.model.Character
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

sealed class CharacterUIState {
    data object Loading : CharacterUIState()

    data class Success(
        val character: Character? = null,
        val allCharacters: Flow<PagingData<Character>> = emptyFlow(),
    ) : CharacterUIState()

    data class Error(
        val message: String,
    ) : CharacterUIState()
}
