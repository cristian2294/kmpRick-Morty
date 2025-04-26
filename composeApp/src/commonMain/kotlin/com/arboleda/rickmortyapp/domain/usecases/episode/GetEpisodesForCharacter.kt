package com.arboleda.rickmortyapp.domain.usecases.episode

import com.arboleda.rickmortyapp.domain.model.Episode
import com.arboleda.rickmortyapp.domain.repository.EpisodeRepository

class GetEpisodesForCharacter(
    private val repository: EpisodeRepository,
) {
    suspend operator fun invoke(episodes: List<String>): List<Episode> = repository.getEpisodesForCharacter(episodes)
}
