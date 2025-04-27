package com.arboleda.rickmortyapp.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.arboleda.rickmortyapp.data.paging.EpisodePagingSource
import com.arboleda.rickmortyapp.data.remote.ApiService
import com.arboleda.rickmortyapp.data.repository.CharacterRepositoryImpl.Companion.MAX_ITEMS
import com.arboleda.rickmortyapp.data.repository.CharacterRepositoryImpl.Companion.PREFETCH_DISTANCE
import com.arboleda.rickmortyapp.domain.model.Episode
import com.arboleda.rickmortyapp.domain.repository.EpisodeRepository
import kotlinx.coroutines.flow.Flow

class EpisodeRepositoryImpl(
    private val apiService: ApiService,
    private val episodePagingSource: EpisodePagingSource,
) : EpisodeRepository {
    override fun getAllEpisodes(): Flow<PagingData<Episode>> =
        Pager(
            config =
                PagingConfig(
                    pageSize = MAX_ITEMS,
                    prefetchDistance = PREFETCH_DISTANCE,
                ),
            pagingSourceFactory = { episodePagingSource },
        ).flow

    override suspend fun getEpisodesForCharacter(episodes: List<String>): List<Episode> {
        if (episodes.isEmpty()) return emptyList()

        return if (episodes.size > 1) {
            try {
                apiService
                    .getMultipleEpisodesById(episodesId = episodes.joinToString(","))
                    .map { it.toDomain() }
            } catch (e: Exception) {
                throw e
            }
        } else {
            try {
                listOf(apiService.getSingleEpisodeById(episodeId = episodes.first()).toDomain())
            } catch (e: Exception) {
                throw e
            }
        }
    }
}
