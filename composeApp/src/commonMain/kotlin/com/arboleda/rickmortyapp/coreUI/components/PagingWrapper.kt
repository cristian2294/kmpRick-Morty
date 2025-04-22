package com.arboleda.rickmortyapp.coreUI.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import app.cash.paging.compose.LazyPagingItems
import com.arboleda.rickmortyapp.domain.model.Character
import com.arboleda.rickmortyapp.ui.screens.home.tabs.characters.CharacterOfTheDay

enum class PagingType {
    ROW,
    COLUM,
    VERTICAL_GRID,
    HORIZONTAL_GRID,
}

@Composable
fun <T : Any> PagingWrapper(
    pagingType: PagingType,
    items: LazyPagingItems<T>,
    initialView: @Composable () -> Unit = {},
    emptyItemsView: @Composable () -> Unit = {},
    loadingMoreItemsView: @Composable () -> Unit = {},
    itemView: @Composable (item: T) -> Unit,
    characterOfTheDay: Character? = null,
) {
    when {
        items.loadState.refresh is LoadState.Loading && items.itemCount == 0 -> {
            initialView()
        }

        items.loadState.refresh is LoadState.NotLoading && items.itemCount == 0 -> {
            emptyItemsView()
        }

        else -> {
            when (pagingType) {
                PagingType.ROW -> {
                    LazyRow {
                        items(items.itemCount) { index ->
                            items[index]?.let {
                                itemView(it)
                            }
                        }
                    }
                }

                PagingType.COLUM ->
                    LazyColumn {
                        items(items.itemCount) { index ->
                            items[index]?.let {
                                itemView(it)
                            }
                        }
                    }

                PagingType.VERTICAL_GRID -> {
                    LazyVerticalGrid(
                        modifier = Modifier.fillMaxSize(),
                        columns = GridCells.Fixed(2),
                        horizontalArrangement = Arrangement.spacedBy(16.dp),
                        verticalArrangement = Arrangement.spacedBy(16.dp),
                    ) {
                        if (characterOfTheDay != null) {
                            item(span = { GridItemSpan(2) }) {
                                CharacterOfTheDay(character = characterOfTheDay)
                            }
                        }
                        items(items.itemCount) { index ->
                            items[index]?.let {
                                itemView(it)
                            }
                        }
                    }
                }

                PagingType.HORIZONTAL_GRID -> {
                    LazyHorizontalGrid(rows = GridCells.Fixed(2)) {
                        items(items.itemCount) { index ->
                            items[index]?.let {
                                itemView(it)
                            }
                        }
                    }
                }
            }

            if (items.loadState.append is LoadState.Loading) {
                loadingMoreItemsView()
            }
        }
    }
}
