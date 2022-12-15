package com.bormotov_vi.domain.user_interactor

import com.bormotov_vi.domain.model.album.Album
import com.bormotov_vi.domain.model.comment.Comment
import com.bormotov_vi.domain.model.photo.Photo
import com.bormotov_vi.domain.model.post.UserPostItem
import com.bormotov_vi.domain.model.user.UsersItem

interface UserRepository {
    suspend fun receiveUsers(): List<UsersItem>
    suspend fun receivePosts(userId: Int?): List<UserPostItem>
    suspend fun receiveComments(postId: Int?): List<Comment>
    suspend fun receiveAlbums(userId: Int?): List<Album>
    suspend fun receivePhotos(albumId: Int?): List<Photo>
}