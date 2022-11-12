package com.myapps.pixabayeye.details.state

sealed interface UiState {
    val isLoading: Boolean
    val errorMessage: String?

    data class DetailsState(
        val userName: String = "",
        val tags: List<String> = listOf(),
        val likes: Int = 0,
        val downloads: Int = 0,
        val comments: Int = 0,
        val previewUrl: String? = null,
        val middleImageUrl: String? = null,
        val largeImageUrl: String? = null,
        override val isLoading: Boolean = false,
        override val errorMessage: String? = null
    ): UiState
}
