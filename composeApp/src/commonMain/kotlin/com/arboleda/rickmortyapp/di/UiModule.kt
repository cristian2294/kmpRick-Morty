package com.arboleda.rickmortyapp.di

import com.arboleda.rickmortyapp.ui.viewModels.CharacterViewModel
import org.koin.compose.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val uiModule =
    module {
        viewModelOf(::CharacterViewModel)
    }
