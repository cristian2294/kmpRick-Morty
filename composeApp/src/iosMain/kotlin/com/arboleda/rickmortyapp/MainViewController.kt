package com.arboleda.rickmortyapp

import androidx.compose.ui.window.ComposeUIViewController
import com.arboleda.rickmortyapp.di.iniKoin

fun MainViewController() = ComposeUIViewController(configure = { iniKoin() }) { App() }
