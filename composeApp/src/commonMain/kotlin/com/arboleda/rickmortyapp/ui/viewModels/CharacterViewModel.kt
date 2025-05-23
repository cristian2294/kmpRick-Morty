package com.arboleda.rickmortyapp.ui.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.arboleda.rickmortyapp.domain.usecases.character.CharacterModule
import com.arboleda.rickmortyapp.ui.states.CharacterUIState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CharacterViewModel(
    private val characterModule: CharacterModule,
) : ViewModel() {
    private val _uiState = MutableStateFlow<CharacterUIState>(CharacterUIState.Loading)
    val uiState: StateFlow<CharacterUIState> = _uiState

    val allCharacters = characterModule.getAllCharactersUC().cachedIn(viewModelScope)

    init {
        getRandomCharacter()
    }

    /**
     * Get a random character from the API
     */
    private fun getRandomCharacter() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                characterModule
                    .getCharacterForIdUC()
                    .onStart {
                        _uiState.value = CharacterUIState.Loading
                    }.catch {
                        _uiState.value =
                            CharacterUIState.Error(message = it.message ?: "Unknown error")
                    }.collect { character ->
                        _uiState.value = CharacterUIState.Success(character = character)
                    }
            }
        }
    }
}
