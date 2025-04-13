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
}
