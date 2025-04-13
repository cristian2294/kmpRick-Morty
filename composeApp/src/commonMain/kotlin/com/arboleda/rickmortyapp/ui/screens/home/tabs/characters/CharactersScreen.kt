package com.arboleda.rickmortyapp.ui.screens.home.tabs.characters

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.paging.LoadState
import app.cash.paging.compose.LazyPagingItems
import app.cash.paging.compose.collectAsLazyPagingItems
import coil3.compose.AsyncImage
import com.arboleda.rickmortyapp.domain.model.Character
import com.arboleda.rickmortyapp.ui.states.CharacterUIState
import com.arboleda.rickmortyapp.ui.viewModels.CharacterViewModel
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI

@OptIn(KoinExperimentalAPI::class)
@Composable
fun CharactersScreen() {
    val characterViewModel = koinViewModel<CharacterViewModel>()
    val uiState by characterViewModel.uiState.collectAsStateWithLifecycle()
    val allCharacters = characterViewModel.allCharacters.collectAsLazyPagingItems()
    HandleUIState(uiState = uiState, allCharacters = allCharacters)
}

@Composable
private fun HandleUIState(
    uiState: CharacterUIState,
    allCharacters: LazyPagingItems<Character>,
) {
    when (uiState) {
        is CharacterUIState.Loading -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        }

        is CharacterUIState.Error -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text(text = uiState.message)
            }
        }

        is CharacterUIState.Success -> {
            ShowCharacterScreen(
                character = uiState.character,
                allCharacters = allCharacters,
            )
        }
    }
}

@Composable
fun ShowCharacterScreen(
    character: Character?,
    allCharacters: LazyPagingItems<Character>,
) {
    CharacterList(characterOfTheDay = character, allCharacters = allCharacters)
}

@Composable
fun CharacterList(
    characterOfTheDay: Character?,
    allCharacters: LazyPagingItems<Character>,
) {
    LazyVerticalGrid(
        modifier = Modifier.fillMaxSize(),
        columns = GridCells.Fixed(2),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        item(span = { GridItemSpan(2) }) {
            Column(modifier = Modifier.fillMaxWidth()) {
                Text(
                    modifier = Modifier.padding(top = 16.dp).align(Alignment.CenterHorizontally),
                    text = "Characters",
                    textAlign = TextAlign.Center,
                    fontSize = 24.sp,
                )
                CharacterOfTheDay(character = characterOfTheDay)
            }
        }
        when {
            allCharacters.loadState.refresh is LoadState.Loading && allCharacters.itemCount == 0 -> {
                // first load
                item(span = { GridItemSpan(2) }) {
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        CircularProgressIndicator()
                    }
                }
            }

            allCharacters.loadState.refresh is LoadState.NotLoading && allCharacters.itemCount == 0 -> {
                // empty items
                item {
                    Text("Empty items")
                }
            }

            else -> {
                items(allCharacters.itemCount) { index ->
                    allCharacters[index]?.let {
                        CharacterItem(character = it)
                    }
                }

                if (allCharacters.loadState.append is LoadState.Loading) {
                    item(span = { GridItemSpan(2) }) {
                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center,
                        ) {
                            CircularProgressIndicator()
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun CharacterItem(character: Character) {
    Box(
        modifier =
            Modifier
                .clip(RoundedCornerShape(24))
                .border(
                    2.dp,
                    Color.Green,
                    shape = RoundedCornerShape(0, 24, 0, 24),
                ).fillMaxSize()
                .clickable { },
        contentAlignment = Alignment.BottomCenter,
    ) {
        AsyncImage(
            modifier = Modifier.fillMaxSize(),
            model = character.image,
            contentDescription = null,
            contentScale = ContentScale.Crop,
        )

        Box(
            modifier =
                Modifier.fillMaxWidth().height(60.dp).background(
                    Brush.verticalGradient(
                        listOf(
                            Color.Black.copy(0f),
                            Color.Black.copy(0.6f),
                            Color.Black.copy(1f),
                        ),
                    ),
                ),
            contentAlignment = Alignment.Center,
        ) {
            Text(
                text = character.name,
                color = Color.White,
                fontSize = 14.sp,
            )
        }
    }
}

@Composable
fun CharacterOfTheDay(character: Character?) {
    Card(
        modifier =
            Modifier
                .fillMaxWidth()
                .height(400.dp)
                .padding(horizontal = 16.dp, vertical = 32.dp),
        shape = RoundedCornerShape(12),
    ) {
        Box(contentAlignment = Alignment.BottomStart) {
            Box(
                modifier =
                    Modifier
                        .fillMaxSize()
                        .background(
                            Color.Green.copy(alpha = 0.5f),
                        ),
            )

            AsyncImage(
                modifier = Modifier.fillMaxSize(),
                model = character?.image,
                contentDescription = "Day Character is ${character?.name}",
                contentScale = ContentScale.Crop,
            )

            Box(
                modifier =
                    Modifier
                        .fillMaxSize()
                        .background(
                            Brush.verticalGradient(
                                0f to Color.White.copy(alpha = 0f),
                                0.8f to Color.Black.copy(alpha = 9f),
                            ),
                        ),
            )
            Text(
                modifier = Modifier.padding(horizontal = 24.dp, vertical = 8.dp).fillMaxWidth(),
                text = character?.name ?: "",
                textAlign = TextAlign.Center,
                overflow = TextOverflow.Ellipsis,
                color = Color.White,
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                maxLines = 1,
                minLines = 1,
            )
        }
    }
}
