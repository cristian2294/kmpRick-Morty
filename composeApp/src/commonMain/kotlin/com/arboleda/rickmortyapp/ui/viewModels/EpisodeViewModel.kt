package com.arboleda.rickmortyapp.ui.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.arboleda.rickmortyapp.domain.model.SeasonEpisode
import com.arboleda.rickmortyapp.domain.usecases.episode.EpisodeModule
import com.arboleda.rickmortyapp.ui.states.EpisodeState
import com.arboleda.rickmortyapp.ui.states.EpisodeUIState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.jetbrains.compose.resources.DrawableResource
import rickmortyapp.composeapp.generated.resources.Res
import rickmortyapp.composeapp.generated.resources.S1
import rickmortyapp.composeapp.generated.resources.S2
import rickmortyapp.composeapp.generated.resources.S3
import rickmortyapp.composeapp.generated.resources.S4
import rickmortyapp.composeapp.generated.resources.S5
import rickmortyapp.composeapp.generated.resources.S6
import rickmortyapp.composeapp.generated.resources.S7

class EpisodeViewModel(
    private val episodeModule: EpisodeModule,
) : ViewModel() {
    private val _episodeState = MutableStateFlow<EpisodeUIState>(EpisodeUIState.Loading)
    val episodeState: StateFlow<EpisodeUIState> = _episodeState

    init {
        getAllEpisodes()
    }

    private fun getAllEpisodes() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                episodeModule
                    .getAllEpisodes()
                    .cachedIn(viewModelScope)
                    .onStart {
                        _episodeState.value = EpisodeUIState.Loading
                    }.catch {
                        _episodeState.value =
                            EpisodeUIState.Error(message = it.message ?: "Unknown error")
                    }.collect { episodes ->
                        _episodeState.value =
                            EpisodeUIState.Success(
                                episodeState =
                                    EpisodeState(
                                        episodes = flowOf(episodes),
                                    ),
                            )
                    }
            }
        }
    }

    fun getSeasonImage(seasonEpisode: SeasonEpisode): DrawableResource =
        when (seasonEpisode) {
            SeasonEpisode.SEASON_1 -> Res.drawable.S1
            SeasonEpisode.SEASON_2 -> Res.drawable.S2
            SeasonEpisode.SEASON_3 -> Res.drawable.S3
            SeasonEpisode.SEASON_4 -> Res.drawable.S4
            SeasonEpisode.SEASON_5 -> Res.drawable.S5
            SeasonEpisode.SEASON_6 -> Res.drawable.S6
            SeasonEpisode.SEASON_7 -> Res.drawable.S7
            SeasonEpisode.SEASON_UNKNOWN -> Res.drawable.S1
        }

    fun onPlaySelected(url: String) {
        val currentState = _episodeState.value
        if (currentState is EpisodeUIState.Success) {
            _episodeState.value =
                EpisodeUIState.Success(
                    episodeState =
                        EpisodeState(
                            episodes = currentState.episodeState.episodes,
                            playVideo = url,
                        ),
                )
        }
    }

    fun onCloseVideoSelected() {
        val currentState = _episodeState.value
        if (currentState is EpisodeUIState.Success) {
            _episodeState.value =
                EpisodeUIState.Success(
                    episodeState =
                        EpisodeState(
                            episodes = currentState.episodeState.episodes,
                            playVideo = "",
                        ),
                )
        }
    }
}
