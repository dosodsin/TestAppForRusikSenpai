package com.bormotov_vi.presentation.users_albums.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bormotov_vi.domain.model.album.Album
import com.bormotov_vi.domain.user_interactor.UsersInteractor
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class UserAlbumViewModel(private val interactor: UsersInteractor) : ViewModel() {

    private val _albums = MutableStateFlow<ScreenState>(ScreenState.Initial)
    val albums: StateFlow<ScreenState> = _albums.asStateFlow()

    fun beforeReceiveAlbums(userId: Int) {
        _albums.value = ScreenState.Loading
        receiveAlbums(userId)
    }

    private fun receiveAlbums(userId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            delay(1000L)
            _albums.value = ScreenState.Success(interactor.receiveAlbums(userId))
        }
    }
}

sealed class ScreenState {
    object Initial : ScreenState()
    object Loading : ScreenState()
    object Error : ScreenState()
    data class Success(val albums: List<Album>) : ScreenState()
}