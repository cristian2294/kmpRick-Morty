package com.arboleda.rickmortyapp.ui.states

import androidx.paging.PagingData
import com.arboleda.rickmortyapp.domain.model.Episode
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

sealed class EpisodeUIState {
    data object Loading : EpisodeUIState()

    data class Success(
        val episodeState: EpisodeState,
    ) : EpisodeUIState()

    data class Error(
        val message: String,
    ) : EpisodeUIState()
}

data class EpisodeState(
    val episodes: Flow<PagingData<Episode>> = emptyFlow(),
    val playVideo: String = "",
)
