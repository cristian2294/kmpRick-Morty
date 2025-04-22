package com.arboleda.rickmortyapp.ui.viewModels

import androidx.lifecycle.ViewModel
import com.arboleda.rickmortyapp.domain.model.Character
import com.arboleda.rickmortyapp.ui.states.CharacterDetailUIState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class CharacterDetailViewModel(
    character: Character,
) : ViewModel() {
    private val _uiState = MutableStateFlow(CharacterDetailUIState(character = character))
    val uiState: StateFlow<CharacterDetailUIState> = _uiState
}
