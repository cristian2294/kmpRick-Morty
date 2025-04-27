package com.arboleda.rickmortyapp.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class OriginDto(
    @SerialName("name")
    val name: String,
)
