package com.bormotov_vi.data

import com.bormotov_vi.domain.model.album.Album
import com.bormotov_vi.domain.model.comment.Comment
import com.bormotov_vi.domain.model.photo.Photo
import com.bormotov_vi.domain.model.post.UserPostItem
import com.bormotov_vi.domain.model.user.UsersItem
import com.bormotov_vi.domain.retrofit.RetrofitInstance.api
import com.bormotov_vi.domain.user_interactor.UserRepository

class UsersRepositoryImpl : UserRepository {

    override suspend fun receiveUsers(): List<UsersItem> {
        return api.getUsers().body()!!
    }

    override suspend fun receivePosts(userId: Int?): List<UserPostItem> {
        return api.getPosts().body()?.filter { it.userId == userId }!!
    }

    override suspend fun receiveComments(postId: Int?): List<Comment> {
        return api.getComments().body()?.filter { it.postId == postId }!!
    }

    override suspend fun receiveAlbums(userId: Int?): List<Album> {
        return api.getAlbums().body()?.filter { it.userId == userId }!!
    }

    override suspend fun receivePhotos(albumId: Int?): List<Photo> {
        return api.getPhotos().body()?.filter { it.albumId == albumId }!!
    }
}