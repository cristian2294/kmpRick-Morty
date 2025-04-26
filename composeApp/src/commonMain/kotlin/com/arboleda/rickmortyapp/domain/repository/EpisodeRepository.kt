package com.arboleda.rickmortyapp.domain.repository

import androidx.paging.PagingData
import com.arboleda.rickmortyapp.domain.model.Episode
import kotlinx.coroutines.flow.Flow

interface EpisodeRepository {
    fun getAllEpisodes(): Flow<PagingData<Episode>>

    suspend fun getEpisodesForCharacter(episodes: List<String>): List<Episode>
}
