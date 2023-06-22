package com.example.appcocoapods

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RandomDuck(
    @SerialName("url") val duckUrl: String
)