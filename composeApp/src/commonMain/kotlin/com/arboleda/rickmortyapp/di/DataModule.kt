package com.arboleda.rickmortyapp.di

import com.arboleda.rickmortyapp.data.paging.CharacterPagingSource
import com.arboleda.rickmortyapp.data.remote.ApiService
import com.arboleda.rickmortyapp.data.repository.CharacterRepositoryImpl
import com.arboleda.rickmortyapp.domain.repository.CharacterRepository
import io.ktor.client.HttpClient
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.http.ContentType
import io.ktor.http.URLProtocol
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val dataModule =
    module {
        single {
            HttpClient {
                install(ContentNegotiation) {
                    json(json = Json { ignoreUnknownKeys = true }, contentType = ContentType.Any)
                }
                install(DefaultRequest) {
                    url {
                        protocol = URLProtocol.HTTPS
                        host = "rickandmortyapi.com"
                        // parameters.append("api_key", "123456789")
                    }
                }
            }
        }

        factoryOf(::ApiService)
        factoryOf(::CharacterPagingSource)
        factory<CharacterRepository> { CharacterRepositoryImpl(get(), get()) }
    }
