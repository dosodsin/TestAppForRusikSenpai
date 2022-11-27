package com.bormotov_vi.domain.model.photo

data class Photo(
    val albumId: Int,
    val id: Int,
    val thumbnailUrl: String,
    val title: String,
    val url: String
)