package com.arboleda.rickmortyapp.data.remote

import com.arboleda.rickmortyapp.data.model.CharacterDto
import com.arboleda.rickmortyapp.data.model.CharacterWrapperDto
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
}
