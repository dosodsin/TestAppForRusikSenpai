package com.bormotov_vi.domain.user_interactor

import com.bormotov_vi.domain.model.album.Album
import com.bormotov_vi.domain.model.comment.Comment
import com.bormotov_vi.domain.model.photo.Photo
import com.bormotov_vi.domain.model.post.UserPostItem
import com.bormotov_vi.domain.model.user.UsersItem

interface UsersInteractor {
    fun receiveUsers(callback: (List<UsersItem>) -> Unit)
    fun receivePosts(userId: Int?, callback: (List<UserPostItem>) -> Unit)
    fun receiveComments(postId: Int?, callback: (List<Comment>) -> Unit)
    fun receiveAlbums(userId: Int?, callback: (List<Album>) -> Unit)
    fun receivePhotos(albumId: Int?, callback: (List<Photo>) -> Unit)
}