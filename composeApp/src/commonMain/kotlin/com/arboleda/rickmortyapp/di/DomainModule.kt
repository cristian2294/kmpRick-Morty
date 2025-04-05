package com.arboleda.rickmortyapp.di

import com.arboleda.rickmortyapp.domain.usecases.CharacterModule
import com.arboleda.rickmortyapp.domain.usecases.GetAllCharacters
import com.arboleda.rickmortyapp.domain.usecases.GetRandomCharacter
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val domainModule =
    module {
        factoryOf(::GetRandomCharacter)
        factoryOf(::GetAllCharacters)
        factoryOf(::CharacterModule)
    }
