package com.bormotov_vi.domain.user_interactor

import com.bormotov_vi.domain.model.album.Album
import com.bormotov_vi.domain.model.comment.Comment
import com.bormotov_vi.domain.model.photo.Photo
import com.bormotov_vi.domain.model.post.UserPostItem
import com.bormotov_vi.domain.model.user.UsersItem
import retrofit2.Call
import retrofit2.http.GET

interface UserApi {
    @GET("users")
    fun getUsers(): Call<List<UsersItem>>

    @GET("posts")
    fun getPosts(): Call<List<UserPostItem>>

    @GET("comments")
    fun getComments(): Call<List<Comment>>

    @GET("albums")
    fun getAlbums(): Call<List<Album>>

    @GET("photos")
    fun getPhotos(): Call<List<Photo>>
}