package com.bormotov_vi.domain.user_interactor

import com.bormotov_vi.domain.model.album.Album
import com.bormotov_vi.domain.model.comment.Comment
import com.bormotov_vi.domain.model.photo.Photo
import com.bormotov_vi.domain.model.post.UserPostItem
import com.bormotov_vi.domain.model.user.UsersItem

interface UserRepository {
    fun receiveUsers(callback: (List<UsersItem>) -> Unit)
    fun receivePosts(callback: (List<UserPostItem>) -> Unit)
    fun receiveComments(callback: (List<Comment>) -> Unit)
    fun receiveAlbums(callback: (List<Album>) -> Unit)
    fun receivePhotos(callback: (List<Photo>) -> Unit)
}