package com.bormotov_vi.presentation.users_albums.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bormotov_vi.domain.model.album.Album
import com.bormotov_vi.domain.user_interactor.UsersInteractor
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserAlbumViewModel(private val interactor: UsersInteractor) : ViewModel() {

    val albums: LiveData<List<Album>> get() = _albums
    private val _albums = MutableLiveData<List<Album>>()

    fun receiveAlbums(userId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            _albums.postValue(interactor.receiveAlbums(userId))
        }
    }
}