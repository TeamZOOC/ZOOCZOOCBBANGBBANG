package org.sopt.zooczoocbbangbbang.data.remote.entity.reord

import kotlinx.serialization.Serializable

@Serializable
data class RequestRecordDto(
    val content: String,
    val photo: String,
    val pet: List<String>
)
