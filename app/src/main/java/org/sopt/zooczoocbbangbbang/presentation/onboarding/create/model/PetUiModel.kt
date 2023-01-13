package org.sopt.zooczoocbbangbbang.presentation.onboarding.create.model

data class PetUiModel(
    var uriString: String,
    var name: String
) {
    companion object {
        val empty = PetUiModel("", "")
    }
}
