package com.arboleda.rickmortyapp.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class EpisodesWrapperDto(
    @SerialName("info")
    val info: InfoDto,
    @SerialName("results")
    val results: List<EpisodeDto>,
)
