package com.arboleda.rickmortyapp.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.arboleda.rickmortyapp.data.remote.ApiService
import com.arboleda.rickmortyapp.domain.model.Character

class CharacterPagingSource(
    private val apiService: ApiService,
) : PagingSource<Int, Character>() {
    override fun getRefreshKey(state: PagingState<Int, Character>): Int? = state.anchorPosition

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Character> =
        try {
            val page = params.key ?: 1
            val response = apiService.getAllCharacter(page)
            val characters = response.results.map { it.toDomain() }
            val prev = if (page > 0) page - 1 else null
            val next = if (response.info.next != null) page + 1 else null
            LoadResult.Page(
                data = characters,
                prevKey = prev,
                nextKey = next,
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
}
