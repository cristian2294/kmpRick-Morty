package com.arboleda.rickmortyapp.di

import com.arboleda.rickmortyapp.ui.viewModels.CharacterDetailViewModel
import com.arboleda.rickmortyapp.ui.viewModels.CharacterViewModel
import com.arboleda.rickmortyapp.ui.viewModels.EpisodeViewModel
import org.koin.compose.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val uiModule =
    module {
        viewModelOf(::CharacterViewModel)
        viewModelOf(::EpisodeViewModel)
        viewModelOf(::CharacterDetailViewModel)
    }
