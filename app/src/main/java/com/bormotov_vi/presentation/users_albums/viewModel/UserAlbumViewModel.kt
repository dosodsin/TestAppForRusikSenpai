package com.bormotov_vi.presentation.users_albums.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bormotov_vi.RusikSenpaiApplication
import com.bormotov_vi.domain.model.album.Album
import com.bormotov_vi.domain.user_interactor.UsersInteractor

class UserAlbumViewModel : ViewModel() {
    private val interactor: UsersInteractor = RusikSenpaiApplication.interactor

    val albums = MutableLiveData<List<Album>>()

    private fun receiveAlbums(userId: Int) {
        interactor.receiveAlbums(userId) { albumItems ->
            albums.postValue(albumItems)
        }
    }

    fun init(userId: Int) {
        receiveAlbums(userId)
    }
}