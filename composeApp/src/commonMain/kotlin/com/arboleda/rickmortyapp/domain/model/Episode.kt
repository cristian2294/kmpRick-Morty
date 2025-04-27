package com.arboleda.rickmortyapp.domain.model

data class Episode(
    val id: Int,
    val name: String,
    val episode: String,
    val characters: List<String>,
    val videoUrl: String,
    val season: SeasonEpisode,
)

enum class SeasonEpisode {
    SEASON_1,
    SEASON_2,
    SEASON_3,
    SEASON_4,
    SEASON_5,
    SEASON_6,
    SEASON_7,
    SEASON_UNKNOWN,
}
