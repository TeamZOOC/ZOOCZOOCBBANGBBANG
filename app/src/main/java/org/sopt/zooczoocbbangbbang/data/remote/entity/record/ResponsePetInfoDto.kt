package org.sopt.zooczoocbbangbbang.data.remote.entity.record

data class ResponsePetInfoDto(
    val status: Int,
    val success: Boolean,
    val message: String,
    val data: List<Pet>
) {
    data class Pet(
        val id: Int,
        val name: String,
        val photo: String
    )
}
