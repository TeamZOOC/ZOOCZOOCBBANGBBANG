package org.sopt.zooczoocbbangbbang.presentation.main.home.data

data class PetData(
    val id: Int,
    val name: String,
    val photo: String?,
    var isSelected: Boolean = false
)
