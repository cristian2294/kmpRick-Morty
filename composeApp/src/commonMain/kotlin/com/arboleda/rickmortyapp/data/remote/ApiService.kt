package com.arboleda.rickmortyapp.data.remote

import com.arboleda.rickmortyapp.data.model.CharacterDto
import com.arboleda.rickmortyapp.data.model.CharacterWrapperDto
import com.arboleda.rickmortyapp.data.model.EpisodeDto
import com.arboleda.rickmortyapp.data.model.EpisodesWrapperDto
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter

class ApiService(
    private val apiClient: HttpClient,
) {
    suspend fun getCharacterForId(id: Int): CharacterDto = apiClient.get("/api/character/$id").body()

    suspend fun getAllCharacter(page: Int): CharacterWrapperDto =
        apiClient
            .get("/api/character/") {
                parameter("page", page)
            }.body()

    suspend fun getAllEpisodes(page: Int): EpisodesWrapperDto =
        apiClient
            .get("/api/episode") {
                parameter("page", page)
            }.body()

    suspend fun getSingleEpisodeById(episodeId: String): EpisodeDto = apiClient.get("/api/episode/$episodeId").body()

    suspend fun getMultipleEpisodesById(episodesId: String): List<EpisodeDto> =
        apiClient.get("/api/episode/$episodesId").body<List<EpisodeDto>>()
}
