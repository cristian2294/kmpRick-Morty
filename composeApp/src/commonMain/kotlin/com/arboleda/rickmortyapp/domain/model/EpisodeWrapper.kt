package com.arboleda.rickmortyapp.domain.model

data class EpisodeWrapper(
    val info: Info,
    val results: List<Episode>,
)
