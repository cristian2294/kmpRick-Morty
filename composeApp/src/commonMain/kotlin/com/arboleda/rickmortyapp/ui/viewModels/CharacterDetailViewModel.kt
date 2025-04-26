package com.arboleda.rickmortyapp.ui.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arboleda.rickmortyapp.domain.model.Character
import com.arboleda.rickmortyapp.domain.usecases.episode.EpisodeModule
import com.arboleda.rickmortyapp.ui.states.CharacterDetailUIState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CharacterDetailViewModel(
    character: Character,
    private val episodeModule: EpisodeModule,
) : ViewModel() {
    private val _uiState = MutableStateFlow(CharacterDetailUIState(character = character))
    val uiState: StateFlow<CharacterDetailUIState> = _uiState

    init {
        getEpisodesForCharacter(character.episode)
    }

    private fun getEpisodesForCharacter(episodes: List<String>) {
        viewModelScope.launch {
            val result =
                withContext(Dispatchers.IO) {
                    episodeModule.getEpisodesForCharacter(episodes)
                }
            _uiState.value = _uiState.value.copy(episodes = result)
        }
    }
}
