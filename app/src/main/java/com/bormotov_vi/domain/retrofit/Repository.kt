package com.bormotov_vi.domain.retrofit

import com.bormotov_vi.domain.model.album.Album
import com.bormotov_vi.domain.model.comment.Comment
import com.bormotov_vi.domain.model.photo.Photo
import com.bormotov_vi.domain.model.post.UserPostItem
import com.bormotov_vi.domain.model.user.UsersItem

class Repository {

    fun getUsers(callback: (List<UsersItem>) -> Unit) {
        RetrofitInstance.api.getUsers(callback)
    }

    fun getPosts(callback: (List<UserPostItem>) -> Unit) {
        RetrofitInstance.api.getPosts(callback)
    }

    fun getComments(callback: (List<Comment>) -> Unit) {
        RetrofitInstance.api.getComments(callback)
    }

    fun getAlbums(callback: (List<Album>) -> Unit) {
        RetrofitInstance.api.getAlbums(callback)
    }

    fun getPhotos(callback: (List<Photo>) -> Unit) {
        RetrofitInstance.api.getPhotos(callback)
    }

}