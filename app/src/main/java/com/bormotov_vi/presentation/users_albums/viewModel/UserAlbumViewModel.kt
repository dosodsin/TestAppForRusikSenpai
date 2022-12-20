package com.bormotov_vi.presentation.users_albums.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bormotov_vi.domain.model.album.Album
import com.bormotov_vi.domain.user_interactor.UsersInteractor
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class UserAlbumViewModel(private val interactor: UsersInteractor) : ViewModel() {

    private val _albums = MutableStateFlow<List<Album>>(emptyList())
    val albums: StateFlow<List<Album>> = _albums.asStateFlow()

    fun receiveAlbums(userId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            _albums.emit(interactor.receiveAlbums(userId))
        }
    }
}