package com.arboleda.rickmortyapp.domain.usecases.episode

import androidx.paging.PagingData
import com.arboleda.rickmortyapp.domain.model.Episode
import com.arboleda.rickmortyapp.domain.repository.EpisodeRepository
import kotlinx.coroutines.flow.Flow

class GetAllEpisodes(
    private val repository: EpisodeRepository,
) {
    operator fun invoke(): Flow<PagingData<Episode>> = repository.getAllEpisodes()
}
