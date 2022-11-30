package com.bormotov_vi.domain.retrofit

import com.bormotov_vi.domain.model.album.Album
import com.bormotov_vi.domain.model.comment.Comment
import com.bormotov_vi.domain.model.photo.Photo
import com.bormotov_vi.domain.model.post.UserPostItem
import com.bormotov_vi.domain.model.user.UsersItem
import retrofit2.Response

class Repository(
    private val api: UserApi
) {

    fun getUsers(callback: (Response<List<UsersItem>>) -> Unit) {
        callback(api.getUsers())
    }

    fun getPosts(callback: (List<UserPostItem>) -> Unit) {
        api.getPosts(callback)
    }

    fun getComments(callback: (List<Comment>) -> Unit) {
        api.getComments(callback)
    }

    fun getAlbums(callback: (List<Album>) -> Unit) {
        api.getAlbums(callback)
    }

    fun getPhotos(callback: (List<Photo>) -> Unit) {
        api.getPhotos(callback)
    }

}