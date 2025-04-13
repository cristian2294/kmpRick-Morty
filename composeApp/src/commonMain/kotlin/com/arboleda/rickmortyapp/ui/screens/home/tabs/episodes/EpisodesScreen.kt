package com.arboleda.rickmortyapp.ui.screens.home.tabs.episodes

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import app.cash.paging.compose.LazyPagingItems
import app.cash.paging.compose.collectAsLazyPagingItems
import com.arboleda.rickmortyapp.coreUI.components.LoadingState
import com.arboleda.rickmortyapp.coreUI.components.PagingType
import com.arboleda.rickmortyapp.coreUI.components.PagingWrapper
import com.arboleda.rickmortyapp.domain.model.Episode
import com.arboleda.rickmortyapp.ui.states.EpisodeUIState
import com.arboleda.rickmortyapp.ui.viewModels.EpisodeViewModel
import org.jetbrains.compose.resources.painterResource
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI

@OptIn(KoinExperimentalAPI::class)
@Composable
fun EpisodesScreen() {
    val episodeViewModel = koinViewModel<EpisodeViewModel>()
    val uiState by episodeViewModel.episodeState.collectAsStateWithLifecycle()

    HandleUiState(uiState = uiState, episodeViewModel = episodeViewModel)
}

@Composable
private fun HandleUiState(
    uiState: EpisodeUIState,
    episodeViewModel: EpisodeViewModel,
) {
    when (uiState) {
        is EpisodeUIState.Error -> {
            Text(text = uiState.message)
        }

        EpisodeUIState.Loading -> {
            CircularProgressIndicator()
        }

        is EpisodeUIState.Success -> {
            val episodes = uiState.episodes
            val lazyPagingItems = episodes.collectAsLazyPagingItems()
            ShowEpisodeScreen(lazyPagingItems, episodeViewModel)
        }
    }
}

@Composable
private fun ShowEpisodeScreen(
    lazyPagingItems: LazyPagingItems<Episode>,
    episodeViewModel: EpisodeViewModel,
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            modifier = Modifier.padding(top = 16.dp).align(Alignment.CenterHorizontally),
            text = "Episodes",
            textAlign = TextAlign.Center,
            fontSize = 24.sp,
        )
        Spacer(modifier = Modifier.height(16.dp))
        PagingWrapper(
            pagingType = PagingType.ROW,
            items = lazyPagingItems,
            initialView = {
                LoadingState()
            },
            itemView = {
                EpisodesList(episodes = it, episodeViewModel = episodeViewModel)
            },
        )
    }
}

@Composable
fun EpisodesList(
    episodes: Episode,
    episodeViewModel: EpisodeViewModel,
) {
    Column(
        modifier = Modifier.width(120.dp).padding(horizontal = 8.dp).clickable { },
    ) {
        Image(
            modifier = Modifier.height(150.dp).fillMaxWidth(),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            painter = painterResource(episodeViewModel.getSeasonImage(episodes.season)), // Usa la variable
        )
    }
}
