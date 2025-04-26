package com.arboleda.rickmortyapp.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil3.compose.AsyncImage
import com.arboleda.rickmortyapp.core.ext.setAliveBorder
import com.arboleda.rickmortyapp.coreUI.BackgroundPrimaryColor
import com.arboleda.rickmortyapp.coreUI.BackgroundSecondaryColor
import com.arboleda.rickmortyapp.coreUI.BackgroundTertiaryColor
import com.arboleda.rickmortyapp.coreUI.DefaultTextColor
import com.arboleda.rickmortyapp.coreUI.Green
import com.arboleda.rickmortyapp.coreUI.Pink
import com.arboleda.rickmortyapp.coreUI.components.LoadingState
import com.arboleda.rickmortyapp.coreUI.components.TextTitle
import com.arboleda.rickmortyapp.domain.model.Character
import com.arboleda.rickmortyapp.domain.model.Episode
import com.arboleda.rickmortyapp.ui.viewModels.CharacterDetailViewModel
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI
import org.koin.core.parameter.parameterSetOf

@OptIn(KoinExperimentalAPI::class)
@Composable
fun CharacterDetailScreen(character: Character) {
    val characterDetailViewModel =
        koinViewModel<CharacterDetailViewModel>(
            parameters = { parameterSetOf(character) },
        )

    val state by characterDetailViewModel.uiState.collectAsStateWithLifecycle()

    Column(
        modifier = Modifier.fillMaxSize().background(BackgroundPrimaryColor),
    ) {
        MainHeader(character = state.character)
        Spacer(modifier = Modifier.height(16.dp))
        Column(
            modifier =
                Modifier
                    .fillMaxSize()
                    .clip(RoundedCornerShape(topStartPercent = 10, topEndPercent = 10))
                    .background(BackgroundSecondaryColor),
        ) {
            CharacterInfo(character = state.character)
            EpisodeList(episodes = state.episodes)
        }
    }
}

@Composable
fun EpisodeList(episodes: List<Episode>) {
    val scrollState = rememberScrollState()
    ElevatedCard(
        modifier = Modifier.padding(horizontal = 16.dp).fillMaxWidth(),
        colors = CardDefaults.elevatedCardColors(containerColor = BackgroundTertiaryColor),
    ) {
        Box(
            modifier = Modifier.padding(16.dp),
            contentAlignment = Alignment.Center,
        ) {
            if (episodes.isEmpty()) {
                LoadingState()
            } else {
                Column(
                    modifier = Modifier.verticalScroll(scrollState),
                ) {
                    TextTitle("Episodes")
                    Spacer(modifier = Modifier.height(4.dp))
                    episodes.forEach { episode ->
                        EpisodeItem(episode = episode)
                        Spacer(modifier = Modifier.height(2.dp))
                    }
                }
            }
        }
    }
}

@Composable
fun EpisodeItem(episode: Episode) {
    Text(text = episode.episode, color = Green, fontWeight = FontWeight.Bold)
    Text(text = episode.name, color = DefaultTextColor)
}

@Composable
fun MainHeader(character: Character) {
    Box(
        modifier = Modifier.fillMaxWidth().height(300.dp).background(Color.Black),
    ) {
        CharacterHeader(character = character)
    }
}

@Composable
fun CharacterHeader(character: Character) {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.BottomCenter) {
        Column(
            modifier =
                Modifier
                    .fillMaxWidth()
                    .height(100.dp)
                    .clip(
                        RoundedCornerShape(
                            topStartPercent = 10,
                            topEndPercent = 10,
                        ),
                    ).background(Color.White),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            Text(
                text = character.name,
                color = Pink,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
            )
            Text(
                text = "Species: ${character.species}",
                color = Color.Black,
            )
        }

        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Spacer(modifier = Modifier.height(16.dp))
            Box(contentAlignment = Alignment.TopCenter) {
                Box(
                    modifier =
                        Modifier
                            .size(205.dp)
                            .clip(CircleShape)
                            .background(Color.Black.copy(alpha = 0.15f)),
                    contentAlignment = Alignment.Center,
                ) {
                    AsyncImage(
                        model = character.image,
                        contentDescription = null,
                        modifier =
                            Modifier
                                .size(190.dp)
                                .clip(CircleShape)
                                .setAliveBorder(character.isAlive),
                        contentScale = ContentScale.Crop,
                    )
                }

                val aliveCopy = if (character.isAlive) "Alive" else "Dead"
                Text(
                    text = aliveCopy,
                    color = Color.White,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    modifier =
                        Modifier
                            .clip(
                                RoundedCornerShape(30),
                            ).background(Color.Black.copy(alpha = 0.6f))
                            .padding(horizontal = 6.dp, vertical = 2.dp),
                )
            }
            Spacer(modifier = Modifier.weight(1f))
        }
    }
}

@Composable
fun CharacterInfo(character: Character) {
    ElevatedCard(
        modifier =
            Modifier
                .fillMaxWidth()
                .padding(16.dp),
        colors = CardDefaults.elevatedCardColors(containerColor = BackgroundTertiaryColor),
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            TextTitle("About The Character")
            Spacer(modifier = Modifier.height(4.dp))
            InformationDetail("Gender: ", character.gender)
            InformationDetail("Origin: ", character.origin)
        }
    }
}

@Composable
fun InformationDetail(
    title: String,
    description: String,
) {
    Row {
        Text(text = title, color = DefaultTextColor, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.width(4.dp))
        Text(text = description, color = Green)
    }
}
