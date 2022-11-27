package com.bormotov_vi.domain.model.post

data class UserPostItem(
    val body: String,
    val id: Int,
    val title: String,
    val userId: Int
)