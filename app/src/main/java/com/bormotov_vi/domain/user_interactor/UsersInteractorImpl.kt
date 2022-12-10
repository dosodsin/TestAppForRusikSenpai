package com.bormotov_vi.domain.user_interactor

import com.bormotov_vi.data.UsersRepositoryImpl
import com.bormotov_vi.domain.model.album.Album
import com.bormotov_vi.domain.model.comment.Comment
import com.bormotov_vi.domain.model.photo.Photo
import com.bormotov_vi.domain.model.post.UserPostItem
import com.bormotov_vi.domain.model.user.UsersItem

class UsersInteractorImpl(
    private val repository: UserRepository
) : UsersInteractor {
    override fun receiveUsers(callback: (List<UsersItem>) -> Unit) =
        repository.receiveUsers(callback)

    override fun receivePosts(userId: Int?, callback: (List<UserPostItem>) -> Unit) =
        repository.receivePosts(userId, callback)

    override fun receiveComments(postId: Int?, callback: (List<Comment>) -> Unit) =
        repository.receiveComments(postId, callback)

    override fun receiveAlbums(userId: Int?, callback: (List<Album>) -> Unit) =
        repository.receiveAlbums(userId, callback)

    override fun receivePhotos(albumId: Int?, callback: (List<Photo>) -> Unit) =
        repository.receivePhotos(albumId, callback)
}