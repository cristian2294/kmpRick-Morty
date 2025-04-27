package com.arboleda.rickmortyapp.di

import com.arboleda.rickmortyapp.domain.usecases.character.CharacterModule
import com.arboleda.rickmortyapp.domain.usecases.character.GetAllCharacters
import com.arboleda.rickmortyapp.domain.usecases.character.GetRandomCharacter
import com.arboleda.rickmortyapp.domain.usecases.episode.EpisodeModule
import com.arboleda.rickmortyapp.domain.usecases.episode.GetAllEpisodes
import com.arboleda.rickmortyapp.domain.usecases.episode.GetEpisodesForCharacter
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val domainModule =
    module {
        // Characters
        factoryOf(::GetRandomCharacter)
        factoryOf(::GetAllCharacters)
        factoryOf(::CharacterModule)

        // Episodes
        factoryOf(::GetAllEpisodes)
        factoryOf(::GetEpisodesForCharacter)
        factoryOf(::EpisodeModule)
    }
