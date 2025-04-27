package com.arboleda.rickmortyapp.data.model

import com.arboleda.rickmortyapp.domain.model.Episode
import com.arboleda.rickmortyapp.domain.model.SeasonEpisode
import com.arboleda.rickmortyapp.domain.model.SeasonEpisode.SEASON_1
import com.arboleda.rickmortyapp.domain.model.SeasonEpisode.SEASON_2
import com.arboleda.rickmortyapp.domain.model.SeasonEpisode.SEASON_3
import com.arboleda.rickmortyapp.domain.model.SeasonEpisode.SEASON_4
import com.arboleda.rickmortyapp.domain.model.SeasonEpisode.SEASON_5
import com.arboleda.rickmortyapp.domain.model.SeasonEpisode.SEASON_6
import com.arboleda.rickmortyapp.domain.model.SeasonEpisode.SEASON_7
import com.arboleda.rickmortyapp.domain.model.SeasonEpisode.SEASON_UNKNOWN
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class EpisodeDto(
    @SerialName("id")
    val id: Int,
    @SerialName("name")
    val name: String,
    @SerialName("episode")
    val episode: String,
    @SerialName("characters")
    val characters: List<String>,
) {
    fun toDomain(): Episode {
        val season = getSeasonFromEpisode(episode)
        return Episode(
            id = id,
            name = name,
            episode = episode,
            characters = characters.map { url -> url.substringAfterLast("/") },
            season = season,
            videoUrl = getVideoUrlFromSeason(season),
        )
    }

    private fun getVideoUrlFromSeason(season: SeasonEpisode): String =
        when (season) {
            SEASON_2 -> "https://ftnfjalrmxkncjjsvbwe.supabase.co/storage/v1/object/public/rmkmp//videoplayback.mp4"
            SEASON_1 -> "https://ftnfjalrmxkncjjsvbwe.supabase.co/storage/v1/object/public/rmkmp//videoplayback.mp4"
            SEASON_3 -> "https://ftnfjalrmxkncjjsvbwe.supabase.co/storage/v1/object/public/rmkmp//videoplayback.mp4"
            SEASON_4 -> "https://ftnfjalrmxkncjjsvbwe.supabase.co/storage/v1/object/public/rmkmp//videoplayback.mp4"
            SEASON_5 -> "https://ftnfjalrmxkncjjsvbwe.supabase.co/storage/v1/object/public/rmkmp//videoplayback.mp4"
            SEASON_6 -> "https://ftnfjalrmxkncjjsvbwe.supabase.co/storage/v1/object/public/rmkmp//videoplayback.mp4"
            SEASON_7 -> "https://ftnfjalrmxkncjjsvbwe.supabase.co/storage/v1/object/public/rmkmp//videoplayback.mp4"
            SEASON_UNKNOWN -> "https://ftnfjalrmxkncjjsvbwe.supabase.co/storage/v1/object/public/rmkmp//videoplayback.mp4"
        }

    private fun getSeasonFromEpisode(episode: String): SeasonEpisode =
        when {
            episode.startsWith("S01") -> SEASON_1
            episode.startsWith("S02") -> SEASON_2
            episode.startsWith("S03") -> SEASON_3
            episode.startsWith("S04") -> SEASON_4
            episode.startsWith("S05") -> SEASON_5
            episode.startsWith("S06") -> SEASON_6
            episode.startsWith("S07") -> SEASON_7
            else -> SEASON_UNKNOWN
        }
}
