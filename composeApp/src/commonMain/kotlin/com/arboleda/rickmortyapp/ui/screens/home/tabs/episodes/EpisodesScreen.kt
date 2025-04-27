package com.arboleda.rickmortyapp.ui.screens.home.tabs.episodes

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import app.cash.paging.compose.LazyPagingItems
import app.cash.paging.compose.collectAsLazyPagingItems
import com.arboleda.rickmortyapp.coreUI.BackgroundPrimaryColor
import com.arboleda.rickmortyapp.coreUI.DefaultTextColor
import com.arboleda.rickmortyapp.coreUI.PlaceholderColor
import com.arboleda.rickmortyapp.coreUI.components.CustomVideoPlayer
import com.arboleda.rickmortyapp.coreUI.components.LoadingState
import com.arboleda.rickmortyapp.coreUI.components.PagingType
import com.arboleda.rickmortyapp.coreUI.components.PagingWrapper
import com.arboleda.rickmortyapp.domain.model.Episode
import com.arboleda.rickmortyapp.ui.states.EpisodeUIState
import com.arboleda.rickmortyapp.ui.viewModels.EpisodeViewModel
import org.jetbrains.compose.resources.painterResource
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI
import rickmortyapp.composeapp.generated.resources.Res
import rickmortyapp.composeapp.generated.resources.placeholder

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
            val episodes = uiState.episodeState.episodes
            val lazyPagingItems = episodes.collectAsLazyPagingItems()
            ShowEpisodeScreen(lazyPagingItems, episodeViewModel, uiState)
        }
    }
}

@Composable
private fun ShowEpisodeScreen(
    lazyPagingItems: LazyPagingItems<Episode>,
    episodeViewModel: EpisodeViewModel,
    uiState: EpisodeUIState.Success,
) {
    Column(modifier = Modifier.fillMaxSize().background(BackgroundPrimaryColor)) {
        Text(
            modifier = Modifier.padding(top = 16.dp).align(Alignment.CenterHorizontally),
            text = "Episodes",
            textAlign = TextAlign.Center,
            fontSize = 24.sp,
            color = DefaultTextColor,
        )
        Spacer(modifier = Modifier.height(16.dp))
        PagingWrapper(
            pagingType = PagingType.ROW,
            items = lazyPagingItems,
            initialView = {
                LoadingState()
            },
            itemView = {
                EpisodesList(episodes = it, episodeViewModel = episodeViewModel) { url ->
                    episodeViewModel.onPlaySelected(url)
                }
            },
        )
        AnimatedContent(uiState.episodeState.playVideo.isNotBlank()) { condition ->
            if (condition) {
                VideoPlayer(uiState = uiState) {
                    episodeViewModel.onCloseVideoSelected()
                }
            } else {
                ElevatedCard(
                    modifier =
                        Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                    colors = CardDefaults.elevatedCardColors(containerColor = PlaceholderColor),
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Image(
                            painter = painterResource(Res.drawable.placeholder),
                            contentDescription = null,
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            modifier = Modifier.padding(16.dp),
                            text = "Please choose and play one episode for show it",
                            color = DefaultTextColor,
                            fontStyle = FontStyle.Italic,
                            fontSize = 12.sp,
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun VideoPlayer(
    uiState: EpisodeUIState.Success,
    onCloseVideoSelected: () -> Unit,
) {
    ElevatedCard(
        modifier =
            Modifier
                .fillMaxWidth()
                .height(250.dp)
                .padding(16.dp)
                .border(2.dp, Color.Green, CardDefaults.elevatedShape),
    ) {
        Box(modifier = Modifier.background(Color.Black)) {
            Box(
                modifier = Modifier.padding(16.dp),
                contentAlignment = Alignment.Center,
            ) {
                CustomVideoPlayer(
                    modifier = Modifier.fillMaxWidth().height(200.dp),
                    url = uiState.episodeState.playVideo,
                )
            }
            Row {
                Spacer(modifier = Modifier.weight(1f))
                IconButton(
                    onClick = { onCloseVideoSelected() },
                    modifier = Modifier.size(40.dp).padding(8.dp),
                    colors =
                        IconButtonDefaults.iconButtonColors(
                            containerColor = Color.DarkGray,
                            contentColor = Color.Green,
                        ),
                ) {
                    Icon(imageVector = Icons.Filled.Close, contentDescription = "Close")
                }
            }
        }
    }
}

@Composable
fun EpisodesList(
    episodes: Episode,
    episodeViewModel: EpisodeViewModel,
    onEpisodeSelected: (String) -> Unit,
) {
    Column(
        modifier =
            Modifier
                .width(120.dp)
                .padding(horizontal = 8.dp)
                .clickable {
                    onEpisodeSelected(episodes.videoUrl)
                },
    ) {
        Image(
            modifier = Modifier.height(150.dp).fillMaxWidth(),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            painter = painterResource(episodeViewModel.getSeasonImage(episodes.season)),
        )
        Spacer(modifier = Modifier.height(2.dp))
        Text(text = episodes.episode, color = DefaultTextColor)
    }
}
