package com.bormotov_vi.domain.user_interactor

import com.bormotov_vi.domain.model.album.Album
import com.bormotov_vi.domain.model.comment.Comment
import com.bormotov_vi.domain.model.photo.Photo
import com.bormotov_vi.domain.model.post.UserPostItem
import com.bormotov_vi.domain.model.user.UsersItem
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET

interface UserApi {
    @GET("users")
    suspend fun getUsers(): Response<List<UsersItem>>

    @GET("posts")
    suspend fun getPosts(): Response<List<UserPostItem>>

    @GET("comments")
    suspend fun getComments(): Response<List<Comment>>

    @GET("albums")
    suspend fun getAlbums(): Response<List<Album>>

    @GET("photos")
    suspend fun getPhotos(): Response<List<Photo>>
}