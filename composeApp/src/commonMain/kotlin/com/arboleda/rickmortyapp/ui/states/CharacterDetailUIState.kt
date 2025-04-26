package com.arboleda.rickmortyapp.ui.states

import com.arboleda.rickmortyapp.domain.model.Character
import com.arboleda.rickmortyapp.domain.model.Episode

data class CharacterDetailUIState(
    val character: Character,
    val episodes: List<Episode> = emptyList(),
)
