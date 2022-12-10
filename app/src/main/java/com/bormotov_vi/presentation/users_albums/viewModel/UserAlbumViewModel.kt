package com.bormotov_vi.presentation.users_albums.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bormotov_vi.domain.model.album.Album
import com.bormotov_vi.domain.user_interactor.UsersInteractor

class UserAlbumViewModel(private val interactor: UsersInteractor) : ViewModel() {

    val albums = MutableLiveData<List<Album>>()
    var userId: Int? = null

    private fun receiveAlbums() {
        interactor.receiveAlbums(userId) { albumItems ->
            albums.postValue(albumItems)
        }
    }

    init {
        receiveAlbums()
    }
}