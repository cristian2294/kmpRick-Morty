package com.arboleda.rickmortyapp.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.arboleda.rickmortyapp.data.remote.ApiService
import com.arboleda.rickmortyapp.domain.model.Episode

class EpisodePagingSource(
    private val apiService: ApiService,
) : PagingSource<Int, Episode>() {
    override fun getRefreshKey(state: PagingState<Int, Episode>): Int? = state.anchorPosition

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Episode> =
        try {
            val page: Int = params.key ?: 1
            val response = apiService.getAllEpisodes(page = page)
            val episodes = response.results.map { it.toDomain() }
            val prev = if (page > 0) page - 1 else null
            val next = if (response.info.next != null) page + 1 else null
            LoadResult.Page(
                data = episodes,
                prevKey = prev,
                nextKey = next,
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
}
