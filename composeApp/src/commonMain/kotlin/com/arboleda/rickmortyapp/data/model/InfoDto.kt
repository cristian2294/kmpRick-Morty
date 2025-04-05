package com.arboleda.rickmortyapp.data.model

import com.arboleda.rickmortyapp.domain.model.Info
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class InfoDto(
    @SerialName("pages")
    val pages: Int,
    @SerialName("next")
    val next: String?,
    @SerialName("prev")
    val prev: String?,
) {
    fun toDomain() = Info(pages = pages, next = next, prev = prev)
}
