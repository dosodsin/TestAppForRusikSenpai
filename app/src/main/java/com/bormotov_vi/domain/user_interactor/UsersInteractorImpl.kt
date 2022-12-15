package com.bormotov_vi.domain.user_interactor

class UsersInteractorImpl(
    private val repository: UserRepository
) : UsersInteractor {
    override suspend fun receiveUsers() = repository.receiveUsers()

    override suspend fun receivePosts(userId: Int?) = repository.receivePosts(userId)

    override suspend fun receiveComments(postId: Int?) = repository.receiveComments(postId)

    override suspend fun receiveAlbums(userId: Int?) = repository.receiveAlbums(userId)

    override suspend fun receivePhotos(albumId: Int?) = repository.receivePhotos(albumId)
}