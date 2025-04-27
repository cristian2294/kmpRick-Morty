package com.arboleda.rickmortyapp.di

import com.arboleda.rickmortyapp.data.local.database.RickMortyDatabase
import com.arboleda.rickmortyapp.data.local.database.getDatabase
import org.koin.core.module.Module
import org.koin.dsl.module

actual fun platformModule(): Module =
    module {
        single<RickMortyDatabase> { getDatabase() }
    }
