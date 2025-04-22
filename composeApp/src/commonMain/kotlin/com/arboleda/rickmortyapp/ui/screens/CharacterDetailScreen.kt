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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
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
import com.arboleda.rickmortyapp.domain.model.Character
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
        modifier = Modifier.fillMaxSize().background(Color.Black),
    ) {
        MainHeader(character = state.character)
        CharacterInfo(character = state.character)
    }
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
                color = Color.Black,
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
    ElevatedCard(modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp)) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text("ABOUT THE CHARACTER")
            Spacer(modifier = Modifier.height(4.dp))
            InformationDetail("Gender: ", character.gender)
            Spacer(modifier = Modifier.height(2.dp))
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
        Text(text = title, color = Color.Black, fontWeight = FontWeight.Bold)
        Text(text = description, color = Color.Green)
    }
}
