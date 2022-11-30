package com.bormotov_vi.domain.retrofit

import com.bormotov_vi.domain.model.album.Album
import com.bormotov_vi.domain.model.comment.Comment
import com.bormotov_vi.domain.model.photo.Photo
import com.bormotov_vi.domain.model.post.UserPostItem
import com.bormotov_vi.domain.model.user.UsersItem
import retrofit2.http.GET

interface UserApi {
    @GET("./users")
    fun getUsers(callback: (List<UsersItem>) -> Unit)

    @GET("./posts")
    fun getPosts(callback: (List<UserPostItem>) -> Unit)

    @GET("./comments")
    fun getComments(callback: (List<Comment>) -> Unit)

    @GET("./albums")
    fun getAlbums(callback: (List<Album>) -> Unit)

    @GET("./photos")
    fun getPhotos(callback: (List<Photo>) -> Unit)
}