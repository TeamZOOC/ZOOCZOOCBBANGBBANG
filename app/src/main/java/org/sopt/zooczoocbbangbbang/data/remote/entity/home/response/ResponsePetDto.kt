package org.sopt.zooczoocbbangbbang.data.remote.entity.home.response

import org.sopt.zooczoocbbangbbang.data.remote.entity.common.Pet

data class ResponsePetDto(
    val status: Int,
    val success: Boolean,
    val message: String,
    val data: List<Pet>
)
