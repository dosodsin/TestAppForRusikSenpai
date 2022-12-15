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
        var userItems: List<UsersItem>? = null
        val response = api.getUsers()
        if (response.isSuccessful) {
            userItems = response.body()!!
        }
        return userItems!!
    }

    override suspend fun receivePosts(userId: Int?): List<UserPostItem> {
        var postItems: List<UserPostItem>? = null
        val response = api.getPosts()
        if (response.isSuccessful) {
            postItems = response.body()?.filter { it.userId == userId }
        }
        return postItems!!
    }

    override suspend fun receiveComments(postId: Int?): List<Comment> {
        var commentItems: List<Comment>? = null
        val response = api.getComments()
        if (response.isSuccessful) {
            commentItems = response.body()?.filter { it.postId == postId }
        }
        return commentItems!!
    }

    override suspend fun receiveAlbums(userId: Int?): List<Album> {
        var albumItems: List<Album>? = null
        val response = api.getAlbums()
        if (response.isSuccessful) {
            albumItems = response.body()?.filter { it.userId == userId }
        }
        return albumItems!!
    }

    override suspend fun receivePhotos(albumId: Int?): List<Photo> {
        var photoItems: List<Photo>? = null
        val response = api.getPhotos()
        if (response.isSuccessful) {
            photoItems = response.body()?.filter { it.albumId == albumId }
        }
        return photoItems!!
    }
}