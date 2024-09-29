package com.android.goally.data.model.api.response.copilot

data class AudioEventX(
    val audioList: List<Audio>? = listOf(),
    val event: String? = ""
)